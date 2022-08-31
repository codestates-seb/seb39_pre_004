package team.pre004.stackoverflowclone.dto.post.response;

import lombok.Builder;
import lombok.Data;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class AnswerInfoDto {

    UserInfoDto owner;

    private Long answerId;

    private String body;
    private Integer likes;
    private boolean isAccepted;
    private String createDate;
    private String modDate;
    private Set<AnswerCommentInfoDto> comments;
}
