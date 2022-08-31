package team.pre004.stackoverflowclone.service;

import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface QuestionCommentService {

    Set<QuestionComment> findAllByQuestion(Long questionId);

    Set<QuestionComment> findAllByUsers(Long usersId);
    QuestionComment save(QuestionComment questionComment);

    QuestionComment update(Long questionId, Long commentId, QuestionCommentDto questionCommentDto);

    QuestionComment findById(Long id);
    void deleteById(Long questionId, Long commentId);

}
