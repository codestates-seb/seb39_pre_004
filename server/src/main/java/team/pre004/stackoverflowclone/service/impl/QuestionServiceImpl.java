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
import team.pre004.stackoverflowclone.dto.post.QuestionDto;
import team.pre004.stackoverflowclone.service.QuestionService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
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
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question save(QuestionDto questionDto) {

        return questionRepository.save(questionDto.toEntity());
    }

    @Override
    public Optional<Question> findById(Long id) {

        return questionRepository.findById(id);
    }

    @Override
    public void deleteById(Long questionId) {
        try {
            questionRepository.deleteById(questionId);
        } catch (EmptyResultDataAccessException ex) {
            log.info("해당하는 게시물이 없습니다 id : " + questionId , ex);
        }
    }


    //좋아요 버튼 관련 Service

    @Override
    public void selectLikeUp(Long userId, Long questionId) {

        //Todo: 좋아요 버튼 클릭 (좋아요가 없는 경우 좋아요 추가, 있는 경우 취소 / 싫어요가 눌려져 있는 경우 싫어요 취소)
        Question question = questionRepository.getReferenceById(questionId);
        Users users = usersRepository.getReferenceById(userId);

        Optional<QuestionLikeUp> byQuestionAndUsersLikeUp = questionLikeUpRepository.findByQuestionAndUsers(question, users);
        Optional<QuestionLikeDown> byQuestionAndUsersLikeDown = questionLikeDownRepository.findByQuestionAndUsers(question, users);

        //해당 질문에 싫어요 버튼이 눌려져 있는 경우
        byQuestionAndUsersLikeDown.ifPresent(
                questionLikeDown -> { //싫어요 취소
                    questionLikeDownRepository.delete(questionLikeDown);
                    question.undoQuestionLikeDown(questionLikeDown);
                }
        );

        //해당 질문에 좋아요 버튼이 눌려져 있는 경우
        byQuestionAndUsersLikeUp.ifPresentOrElse(
                questionLikeUp -> { //좋아요 취소
                    questionLikeUpRepository.delete(questionLikeUp);
                    question.undoQuestionLikeUp(questionLikeUp);
                },
                () -> { //좋아요 추가
                   QuestionLikeUp questionLikeUp = QuestionLikeUp.builder().build();

                    questionLikeUp.mappingQuestion(question);
                    questionLikeUp.mappingUsers(users);
                    question.updateLikeCount();

                    questionLikeUpRepository.save(questionLikeUp);

                }

        );
    }

    @Override
    public void selectLikeDown(Long userId, Long questionId) {

        //Todo: 싫어요 버튼 클릭 (싫어요 가 없는 경우 싫어요  추가, 있는 경우 취소 / 좋아요가 눌려져 있는 경우 좋아요 취소)
        Question question = questionRepository.getReferenceById(questionId);
        Users users = usersRepository.getReferenceById(userId);

        Optional<QuestionLikeUp> byQuestionAndUsersLikeUp = questionLikeUpRepository.findByQuestionAndUsers(question, users);
        Optional<QuestionLikeDown> byQuestionAndUsersLikeDown = questionLikeDownRepository.findByQuestionAndUsers(question, users);

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
                    questionLikeDownRepository.delete(questionLikeDown);
                    question.undoQuestionLikeDown(questionLikeDown);
                },
                () -> { //싫어요 추가
                    QuestionLikeDown questionLikeDown = QuestionLikeDown.builder().build();

                    questionLikeDown.mappingQuestion(question);
                    questionLikeDown.mappingUsers(users);
                    question.updateLikeCount();

                    questionLikeDownRepository.save(questionLikeDown);

                }

        );

    }

}
