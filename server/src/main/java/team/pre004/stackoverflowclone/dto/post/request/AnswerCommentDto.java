package team.pre004.stackoverflowclone.dto.post.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

@NoArgsConstructor
@Data
public class AnswerCommentDto {
    private Users owner;
    private String body;
    private Answer answer;

    @Builder
    public AnswerCommentDto(String body, Answer answer) {
        this.body = body;
        this.answer = answer;
    }
}
