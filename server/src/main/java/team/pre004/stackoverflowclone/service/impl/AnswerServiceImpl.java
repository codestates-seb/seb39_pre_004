package team.pre004.stackoverflowclone.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.pre004.stackoverflowclone.domain.post.entity.*;
import team.pre004.stackoverflowclone.domain.post.repository.AnswerLikeDownRepository;
import team.pre004.stackoverflowclone.domain.post.repository.AnswerLikeUpRepository;
import team.pre004.stackoverflowclone.domain.post.repository.AnswerRepository;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomLikesConflictException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotAccessItemsException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointUsersException;
import team.pre004.stackoverflowclone.service.AnswerService;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerLikeUpRepository answerLikeUpRepository;
    private final AnswerLikeDownRepository answerLikeDownRepository;
    private final UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public Set<Answer> findAll() {
        return answerRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Answer> findAllByQuestion(Question question) {

        return answerRepository.findAllByQuestion(question);
    }

    @Override
    @Transactional
    public Answer save(Answer answer) {

        return answerRepository.save(answer);
    }

    @Override
    @Transactional
    public Answer update(Long answerId, Answer answer) {

        Answer updateAnswer = answerRepository.findById(answerId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID)
        );
        updateAnswer.update(answer.getBody());
        return updateAnswer;
    }

    @Override
    public Optional<Answer> findById(Long id) {
        return answerRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            answerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new CustomLikesConflictException(ExceptionMessage.NOT_CONTENT_ANSWER_ID);
        }
    }

    @Override
    @Transactional
    public boolean acceptAnswer(Long userId, Long answerId) {

        Users owner = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );

        Answer answer = answerRepository.findById(answerId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID)
        );

        if(owner != answer.getOwner())
            throw new CustomNotAccessItemsException(ExceptionMessage.NOT_ACCESS_EDIT_ANSWER_ACCESS);

        Question question = answer.getQuestion();

        try {
            //다른 답글을 취소합니다.
            for(Answer ans : question.getAnswers()) {
                ans.accept(false);
            }

            answer.accept(true);
            question.accept(true);

            return answer.isAccepted();

        } catch (Exception e) {
            throw new CustomNotAccessItemsException("답글을 취소할 수 없습니다.");
        }

    }

    @Override
    @Transactional
    public boolean acceptAnswerUndo(Long userId, Long answerId) {
        Users owner = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );

        Answer answer = answerRepository.findById(answerId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID)
        );

        if(owner != answer.getOwner())
            throw new CustomNotAccessItemsException(ExceptionMessage.NOT_ACCESS_EDIT_ANSWER_ACCESS);

        Question question = answer.getQuestion();

        try {

            answer.accept(false);
            question.accept(false);

            return answer.isAccepted();

        } catch (Exception e) {
            throw new CustomNotAccessItemsException("취소를 실패하였습니다.");
        }
    }

    @Override
    @Transactional
    public Integer selectLikeUp(Long userId, Long answerId) {

        Answer answer = answerRepository.findById(answerId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users owner = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );
        Optional<AnswerLikeUp> byAnswerAndOwnerLikeUp = answerLikeUpRepository.findByAnswerAndOwner(answer, owner);
        Optional<AnswerLikeDown> byAnswerAndOwnerLikeDown = answerLikeDownRepository.findByAnswerAndOwner(answer, owner);

        //해당 질문에 싫어요 버튼이 눌려져 있는 경우
        byAnswerAndOwnerLikeDown.ifPresent(
                answerLikeDown -> { //싫어요 취소
                    answerLikeDownRepository.delete(answerLikeDown);
                    answer.undoAnswerLikeDown(answerLikeDown);
                }
        );

        byAnswerAndOwnerLikeUp.ifPresentOrElse(//해당 질문에 좋아요 버튼이 눌려져 있는 경우
                answerLikeUp -> { //좋아요 취소
                    throw new CustomLikesConflictException(ExceptionMessage.CONFLICT_LIKE_UP);
                },
                () -> { //좋아요 추가
                    AnswerLikeUp answerLikeUp = AnswerLikeUp.builder().build();
                    answerLikeUp.mappingQuestion(answer);
                    answerLikeUp.mappingUsers(owner);
                    answerLikeUpRepository.save(answerLikeUp);
                }
        );
        answer.updateLikeCount();
        return answer.getLikes();
    }

    @Override
    @Transactional
    public Integer selectLikeUpUndo(Long userId, Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users owner = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );
        Optional<AnswerLikeUp> byAnswerAndOwnerLikeUp = answerLikeUpRepository.findByAnswerAndOwner(answer, owner);


        byAnswerAndOwnerLikeUp.ifPresentOrElse(//해당 질문에 좋아요 버튼이 눌려져 있는 경우
                answerLikeUp -> { //좋아요 취소
                    answerLikeUpRepository.delete(answerLikeUp);
                    answer.undoAnswerLikeUp(answerLikeUp);
                },
                () -> { //좋아요 추가
                    throw new CustomLikesConflictException(ExceptionMessage.CONFLICT_LIKE_UP_UNDO);
                }
        );
        answer.updateLikeCount();
        return answer.getLikes();
    }

    @Override
    @Transactional
    public Integer selectLikeDown(Long userId, Long answerId) {

        Answer answer = answerRepository.findById(answerId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users owner = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );
        Optional<AnswerLikeUp> byAnswerAndOwnerLikeUp = answerLikeUpRepository.findByAnswerAndOwner(answer, owner);
        Optional<AnswerLikeDown> byAnswerAndOwnerLikeDown = answerLikeDownRepository.findByAnswerAndOwner(answer, owner);


        byAnswerAndOwnerLikeUp.ifPresent(
                answerLikeup -> {
                    answerLikeUpRepository.delete(answerLikeup);
                    answer.undoAnswerLikeUp(answerLikeup);
                }
        );

        byAnswerAndOwnerLikeDown.ifPresentOrElse(
                answerLikeUp -> {
                    throw new CustomLikesConflictException(ExceptionMessage.CONFLICT_LIKE_DOWN);
                },
                () -> { //좋아요 추가
                    AnswerLikeDown answerLikeDown = AnswerLikeDown.builder().build();
                    answerLikeDown.mappingQuestion(answer);
                    answerLikeDown.mappingUsers(owner);
                    answerLikeDownRepository.save(answerLikeDown);
                }
        );
        answer.updateLikeCount();
        return answer.getLikes();
    }

    @Override
    @Transactional
    public Integer selectLikeDownUndo(Long userId, Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        Users owner = usersRepository.findById(userId).orElseThrow(
                () -> new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID)
        );
        Optional<AnswerLikeDown> byAnswerAndOwnerLikeDown = answerLikeDownRepository.findByAnswerAndOwner(answer, owner);

        //해당 질문에 싫어요 버튼이 눌려져 있는 경우
        byAnswerAndOwnerLikeDown.ifPresentOrElse(//해당 질문에 좋아요 버튼이 눌려져 있는 경우
                answerLikeDown -> { //좋아요 취소
                    answerLikeDownRepository.delete(answerLikeDown);
                    answer.undoAnswerLikeDown(answerLikeDown);
                },
                () -> { //좋아요 추가
                    throw new CustomLikesConflictException(ExceptionMessage.CONFLICT_LIKE_DOWN_UNDO);
                }
        );
        answer.updateLikeCount();
        return answer.getLikes();
    }
}
