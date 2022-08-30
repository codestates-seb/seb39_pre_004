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
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomLikesConflictException;
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
    public boolean acceptAnswer(Long userId, Long answerId) {
        return false;
    }

    @Override
    public boolean acceptAnswerUndo(Long userId, Long answerId) {
        return false;
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
