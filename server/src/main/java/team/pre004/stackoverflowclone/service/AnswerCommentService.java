package team.pre004.stackoverflowclone.service;

import team.pre004.stackoverflowclone.domain.post.entity.AnswerComment;
import team.pre004.stackoverflowclone.dto.post.request.AnswerCommentDto;


import java.util.List;

public interface AnswerCommentService {

    List<AnswerComment> findAllByAnswer(Long answerId);
    List<AnswerComment> findAllByUsers(Long usersId);
    AnswerComment save(AnswerComment answerComment);
    AnswerComment update(Long answerId, Long commentId, AnswerCommentDto answerCommentDto);
    AnswerComment findById(Long id);
    void deleteById(Long answerId, Long commentId);

}
