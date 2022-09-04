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
    public Question update(Long id, Question question) {

        Question updateQuestion = questionRepository.findById(id).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );

        updateQuestion.update(
                question.getTitle(),
                question.getBody(),
                question.getTags()
        );

        return updateQuestion;

    }

    @Override
    public Optional<Question> findById(Long id) {

        return Optional.ofNullable(questionRepository.findById(id).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        ));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        //Todo: 해당 게시판을 삭제합니다.
        try {
            questionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new CustomLikesConflictException(ExceptionMessage.NOT_CONTENT_QUESTION_ID);
        }
    }


    //좋아요 버튼 관련 Service

    @Override
    @Transactional
    public Integer selectLikeUp(Long userId, Long questionId) {

        //Todo: 좋아요 버튼 클릭 (좋아요가 없는 경우 좋아요 추가, 싫어요가 눌려져 있는 경우 싫어요 취소 후 좋아요)
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );
        Optional<QuestionLikeUp> byQuestionAndUsersLikeUp = questionLikeUpRepository.findByQuestionAndOwner(question, users);
        Optional<QuestionLikeDown> byQuestionAndUsersLikeDown = questionLikeDownRepository.findByQuestionAndOwner(question, users);

        //해당 질문에 싫어요 버튼이 눌려져 있는 경우
        byQuestionAndUsersLikeDown.ifPresent(
                questionLikeDown -> { //싫어요 취소
                    questionLikeDownRepository.delete(questionLikeDown);
                    question.undoQuestionLikeDown(questionLikeDown);
                }
        );

        byQuestionAndUsersLikeUp.ifPresentOrElse(//해당 질문에 좋아요 버튼이 눌려져 있는 경우
                questionLikeUp -> { //좋아요 취소
                    throw new CustomLikesConflictException(ExceptionMessage.CONFLICT_LIKE_UP);
                },
                () -> { //좋아요 추가
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

        //Todo: 좋아요 버튼 클릭 (좋아요가 있는 경우 취소 )
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );
        Optional<QuestionLikeUp> byQuestionAndUsersLikeUp = questionLikeUpRepository.findByQuestionAndOwner(question, users);

        byQuestionAndUsersLikeUp.ifPresentOrElse(//해당 질문에 좋아요 버튼이 눌려져 있는 경우
                questionLikeUp -> { //좋아요 취소
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

        //Todo: 싫어요 버튼 클릭 (싫어요 가 없는 경우 싫어요  추가, 있는 경우 취소 / 좋아요가 눌려져 있는 경우 좋아요 취소)
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );
        Optional<QuestionLikeUp> byQuestionAndUsersLikeUp = questionLikeUpRepository.findByQuestionAndOwner(question, users);
        Optional<QuestionLikeDown> byQuestionAndUsersLikeDown = questionLikeDownRepository.findByQuestionAndOwner(question, users);
        //해당 질문에 좋아요 버튼이 눌려져 있는 경우
        byQuestionAndUsersLikeUp.ifPresent(
                questionLikeUp -> { //좋아요 취소
                    questionLikeUpRepository.delete(questionLikeUp);
                    question.undoQuestionLikeUp(questionLikeUp);
                }
        );
        //해당 싫어요 좋아요 버튼이 눌려져 있는 경우
        byQuestionAndUsersLikeDown.ifPresentOrElse(
                questionLikeDown -> { //싫어요 취소
                    throw new CustomLikesConflictException(ExceptionMessage.CONFLICT_LIKE_DOWN);
                },
                () -> { //싫어요 추가
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
        //Todo: 싫어요 버튼 클릭 (싫어요 가 없는 경우 싫어요  추가, 있는 경우 취소 / 좋아요가 눌려져 있는 경우 좋아요 취소)
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );

        Optional<QuestionLikeDown> byQuestionAndUsersLikeDown = questionLikeDownRepository.findByQuestionAndOwner(question, users);

        //해당 싫어요 좋아요 버튼이 눌려져 있는 경우
        byQuestionAndUsersLikeDown.ifPresentOrElse(
                questionLikeDown -> { //싫어요 취소
                    questionLikeDownRepository.delete(questionLikeDown);
                    question.undoQuestionLikeDown(questionLikeDown);
                },
                () -> { //싫어요 추가
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
