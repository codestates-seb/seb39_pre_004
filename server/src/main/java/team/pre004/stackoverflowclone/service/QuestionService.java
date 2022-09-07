package team.pre004.stackoverflowclone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.pre004.stackoverflowclone.domain.post.entity.Question;

import java.util.Optional;
import java.util.Set;

public interface QuestionService {
    Page<Question> findAll(Pageable pageable);
    Set<Question> findAll();
    Question save(Question question);
    Question update(Long id, Question question);
    Optional<Question> findById(Long id);
    void deleteById(Long id);
    Integer selectLikeUp(Long userId, Long questionId);
    Integer selectLikeUpUndo(Long userId, Long questionId);
    Integer selectLikeDown(Long userId, Long questionId);
    Integer selectLikeDownUndo(Long userId, Long questionId);

    int updateView(Long id);

}
