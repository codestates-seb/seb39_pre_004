package team.pre004.stackoverflowclone.service;

import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.Question;

import java.util.Optional;
import java.util.Set;

public interface AnswerService {

    Set<Answer> findAll();
    Set<Answer> findAllByQuestion(Question question);

    Answer save(Answer answer);
    Answer update(Long answerId, Answer answer);
    Optional<Answer> findById(Long id);


    void deleteById(Long id);
    boolean acceptAnswer(Long userId, Long answerId);
    boolean acceptAnswerUndo(Long userId, Long answerId);
    Integer selectLikeUp(Long userId, Long answerId);
    Integer selectLikeUpUndo(Long userId, Long answerId);
    Integer selectLikeDown(Long userId, Long answerId);
    Integer selectLikeDownUndo(Long userId, Long answerId);

}
