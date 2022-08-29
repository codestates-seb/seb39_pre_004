package team.pre004.stackoverflowclone.service;

import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.dto.post.QuestionCommentDto;

import java.util.List;
import java.util.Optional;


public interface QuestionCommentService {

    List<QuestionComment> findAllByQuestion(Long questionId);

    List<QuestionComment> findAllByUsers(Long usersId);
    QuestionComment save(Long questionId, QuestionCommentDto questionCommentDto);
    Optional<QuestionComment> findById(Long id);
    void deleteById(Long Id);

}
