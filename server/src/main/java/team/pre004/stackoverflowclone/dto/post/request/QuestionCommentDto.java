package team.pre004.stackoverflowclone.dto.post.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;


@NoArgsConstructor
@Data
public class QuestionCommentDto {

    private String body;

    private Question question;


    @Builder
    public QuestionCommentDto(String body, Question question) {
        this.body = body;
        this.question = question;
    }
}
