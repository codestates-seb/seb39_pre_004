package team.pre004.stackoverflowclone.service;

import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface QuestionCommentService {

    List<QuestionComment> findAllByQuestion(Long questionId);

    List<QuestionComment> findAllByUsers(Long usersId);
    QuestionComment save(QuestionComment questionComment);

    QuestionComment update(Long commentId, QuestionComment questionComment);

    QuestionComment findById(Long id);
    void deleteById(Long id);

}
