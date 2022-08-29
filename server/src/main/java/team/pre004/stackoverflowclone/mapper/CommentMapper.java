package team.pre004.stackoverflowclone.mapper;

import org.mapstruct.Mapper;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.dto.post.response.QuestionCommentInfoDto;


import java.util.Set;

@Mapper
public interface CommentMapper {
    Set<QuestionCommentInfoDto> getQuestionCommentInfo(Set<QuestionComment> questionComment);
}
