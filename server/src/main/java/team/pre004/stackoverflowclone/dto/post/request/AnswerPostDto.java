package team.pre004.stackoverflowclone.dto.post.request;

import lombok.*;
import team.pre004.stackoverflowclone.dto.post.response.UserInfoDto;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AnswerPostDto {
    UserInfoDto owner;
    private Long answerId;
    private String body;
    private String link;
}
