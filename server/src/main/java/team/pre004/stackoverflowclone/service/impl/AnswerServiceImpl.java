package team.pre004.stackoverflowclone.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.repository.AnswerLikeDownRepository;
import team.pre004.stackoverflowclone.domain.post.repository.AnswerLikeUpRepository;
import team.pre004.stackoverflowclone.domain.post.repository.AnswerRepository;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomLikesConflictException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotAccessItemsException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
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
    public Integer selectLikeUp(Long userId, Long answerId) {




        return null;
    }

    @Override
    public Integer selectLikeUpUndo(Long userId, Long answerId) {
        return null;
    }

    @Override
    public Integer selectLikeDown(Long userId, Long answerId) {
        return null;
    }

    @Override
    public Integer selectLikeDownUndo(Long userId, Long answerId) {
        return null;
    }
}
