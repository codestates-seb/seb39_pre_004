package team.pre004.stackoverflowclone.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionLikeDown;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionLikeUp;
import team.pre004.stackoverflowclone.domain.post.repository.QuestionLikeDownRepository;
import team.pre004.stackoverflowclone.domain.post.repository.QuestionLikeUpRepository;
import team.pre004.stackoverflowclone.domain.post.repository.QuestionRepository;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.dto.post.request.QuestionPostDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomLikesConflictException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointUsersException;
import team.pre004.stackoverflowclone.service.QuestionService;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final QuestionLikeUpRepository questionLikeUpRepository;
    private final QuestionLikeDownRepository questionLikeDownRepository;
    private final UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Question> findAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Question> findAll() {
        return questionRepository.findAllBy();
    }

    @Override
    @Transactional
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    @Transactional
    public Question update(Long id, QuestionPostDto questionPostDto) {

        Question updateQuestion = questionRepository.findById(id).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );

        updateQuestion.update(
                questionPostDto.getTitle(),
                questionPostDto.getBody(),
                questionPostDto.getTags()
        );

        return updateQuestion;

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Question> findById(Long id) {

        return Optional.ofNullable(questionRepository.findById(id).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        ));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        //Todo: ?????? ???????????? ???????????????.
        try {
            questionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new CustomLikesConflictException(ExceptionMessage.NOT_CONTENT_QUESTION_ID);
        }
    }


    //????????? ?????? ?????? Service

    @Override
    @Transactional
    public Integer selectLikeUp(Long userId, Long questionId) {

        //Todo: ????????? ?????? ?????? (???????????? ?????? ?????? ????????? ??????, ???????????? ????????? ?????? ?????? ????????? ?????? ??? ?????????)
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );
        Optional<QuestionLikeUp> byQuestionAndUsersLikeUp = questionLikeUpRepository.findByQuestionAndOwner(question, users);
        Optional<QuestionLikeDown> byQuestionAndUsersLikeDown = questionLikeDownRepository.findByQuestionAndOwner(question, users);

        //?????? ????????? ????????? ????????? ????????? ?????? ??????
        byQuestionAndUsersLikeDown.ifPresent(
                questionLikeDown -> { //????????? ??????
                    questionLikeDownRepository.delete(questionLikeDown);
                    question.undoQuestionLikeDown(questionLikeDown);
                }
        );

        byQuestionAndUsersLikeUp.ifPresentOrElse(//?????? ????????? ????????? ????????? ????????? ?????? ??????
                questionLikeUp -> { //????????? ??????
                    throw new CustomLikesConflictException(ExceptionMessage.CONFLICT_LIKE_UP);
                },
                () -> { //????????? ??????
                   QuestionLikeUp questionLikeUp = QuestionLikeUp.builder().build();
                    questionLikeUp.mappingQuestion(question);
                    questionLikeUp.mappingUsers(users);
                    questionLikeUpRepository.save(questionLikeUp);
                }
        );
        question.updateLikeCount();
        return question.getLikes();
    }

    @Override
    @Transactional
    public Integer selectLikeUpUndo(Long userId, Long questionId) {

        //Todo: ????????? ?????? ?????? (???????????? ?????? ?????? ?????? )
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );
        Optional<QuestionLikeUp> byQuestionAndUsersLikeUp = questionLikeUpRepository.findByQuestionAndOwner(question, users);

        byQuestionAndUsersLikeUp.ifPresentOrElse(//?????? ????????? ????????? ????????? ????????? ?????? ??????
                questionLikeUp -> { //????????? ??????
                    questionLikeUpRepository.delete(questionLikeUp); //
                    question.undoQuestionLikeUp(questionLikeUp);
                },
                () -> {
                    throw new CustomLikesConflictException(ExceptionMessage.CONFLICT_LIKE_UP_UNDO);
                }
        );
        question.updateLikeCount();
        return question.getLikes();
    }

    @Override
    @Transactional
    public Integer selectLikeDown(Long userId, Long questionId) {

        //Todo: ????????? ?????? ?????? (????????? ??? ?????? ?????? ?????????  ??????, ?????? ?????? ?????? / ???????????? ????????? ?????? ?????? ????????? ??????)
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );
        Optional<QuestionLikeUp> byQuestionAndUsersLikeUp = questionLikeUpRepository.findByQuestionAndOwner(question, users);
        Optional<QuestionLikeDown> byQuestionAndUsersLikeDown = questionLikeDownRepository.findByQuestionAndOwner(question, users);
        //?????? ????????? ????????? ????????? ????????? ?????? ??????
        byQuestionAndUsersLikeUp.ifPresent(
                questionLikeUp -> { //????????? ??????
                    questionLikeUpRepository.delete(questionLikeUp);
                    question.undoQuestionLikeUp(questionLikeUp);
                }
        );
        //?????? ????????? ????????? ????????? ????????? ?????? ??????
        byQuestionAndUsersLikeDown.ifPresentOrElse(
                questionLikeDown -> { //????????? ??????
                    throw new CustomLikesConflictException(ExceptionMessage.CONFLICT_LIKE_DOWN);
                },
                () -> { //????????? ??????
                    QuestionLikeDown questionLikeDown = QuestionLikeDown.builder().build();
                    questionLikeDown.mappingQuestion(question);
                    questionLikeDown.mappingUsers(users);
                    questionLikeDownRepository.save(questionLikeDown);
                }
        );
        question.updateLikeCount();
        return question.getLikes();
    }


    @Override
    @Transactional
    public Integer selectLikeDownUndo(Long userId, Long questionId) {
        //Todo: ????????? ?????? ?????? (????????? ??? ?????? ?????? ?????????  ??????, ?????? ?????? ?????? / ???????????? ????????? ?????? ?????? ????????? ??????)
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );

        Optional<QuestionLikeDown> byQuestionAndUsersLikeDown = questionLikeDownRepository.findByQuestionAndOwner(question, users);

        //?????? ????????? ????????? ????????? ????????? ?????? ??????
        byQuestionAndUsersLikeDown.ifPresentOrElse(
                questionLikeDown -> { //????????? ??????
                    questionLikeDownRepository.delete(questionLikeDown);
                    question.undoQuestionLikeDown(questionLikeDown);
                },
                () -> { //????????? ??????
                    throw new CustomLikesConflictException(ExceptionMessage.CONFLICT_LIKE_DOWN_UNDO);
                }
        );

        question.updateLikeCount();
        return question.getLikes();
    }

    @Override
    @Transactional
    public int updateView(Long id) {
        return questionRepository.updateView(id);
    }


}
