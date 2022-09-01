package team.pre004.stackoverflowclone.dto.post.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
public class AnswerCommentInfoDto {
    private UserInfoDto owner;
    private Long answerCommentId;
    private String body;
    private String createDate;
    private String modDate;
}
