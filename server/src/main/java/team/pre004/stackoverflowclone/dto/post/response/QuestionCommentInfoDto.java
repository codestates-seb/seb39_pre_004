package team.pre004.stackoverflowclone.dto.post.response;

import lombok.Builder;
import lombok.Data;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import java.time.LocalDateTime;

@Data
@Builder
public class QuestionCommentInfoDto {

    private UserInfoDto owner;
    private String body;

    private LocalDateTime createDate;
    private LocalDateTime modDate;
}
