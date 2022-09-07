package team.pre004.stackoverflowclone.dto.post.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class AnswerCommentInfoDto {
    private UserInfoDto owner;
    private Long answerCommentId;
    private String body;
    private String createDate;
    private String modDate;
}
