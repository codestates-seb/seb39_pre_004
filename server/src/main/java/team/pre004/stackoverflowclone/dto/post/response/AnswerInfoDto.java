package team.pre004.stackoverflowclone.dto.post.response;

import lombok.Builder;
import lombok.Data;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class AnswerInfoDto {

    private UserInfoDto owner;
    private Integer likes;
    private String body;

    private LocalDateTime createDate;
    private LocalDateTime modDate;
    private Set<QuestionCommentDto> comments;
}
