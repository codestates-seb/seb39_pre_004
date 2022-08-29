package team.pre004.stackoverflowclone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.dto.post.QuestionDto;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Page<Question> findAll(Pageable pageable);
    List<Question> findAll();
    Question save(QuestionDto questionDto);
    Optional<Question> findById(Long id);
    void deleteById(Long id);
    Integer selectLikeUp(Long userId, Long questionId);
    Integer selectLikeDown(Long userId, Long questionId);

}
