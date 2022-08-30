package team.pre004.stackoverflowclone.dto.post.response;

import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Builder
public class QuestionCommentInfoDto {


    private UserInfoDto owner;
    private Long questionCommentId;
    private String body;
    private LocalDateTime createDate;
    private LocalDateTime modDate;
}
