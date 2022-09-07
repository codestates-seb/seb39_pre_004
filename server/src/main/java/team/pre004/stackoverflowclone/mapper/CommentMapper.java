package team.pre004.stackoverflowclone.mapper;

import org.mapstruct.Mapper;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.AnswerComment;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.AnswerCommentDto;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;
import team.pre004.stackoverflowclone.dto.post.response.AnswerCommentInfoDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionCommentInfoDto;


import java.util.Set;

public interface CommentMapper {
    Set<QuestionCommentInfoDto> getQuestionCommentInfos(Set<QuestionComment> questionComment);

    QuestionComment getQuestionComment(Users owner, Long questionId, QuestionCommentDto questionCommentDto);
    QuestionCommentInfoDto getQuestionCommentInfo(QuestionComment questionComment);
    Set<AnswerCommentInfoDto> getAnswerCommentInfos(Set<AnswerComment> answerComments);
    AnswerComment getAnswerComment(Users owner, Long questionId, AnswerCommentDto answerCommentDto);
    AnswerCommentInfoDto getAnswerCommentInfo(AnswerComment answerComment);

}
