package team.pre004.stackoverflowclone.dto.post.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.dto.post.response.UserInfoDto;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerPostDto {
    UserInfoDto owner;
    private Long answerId;
    private String body;
    private String link;
}
