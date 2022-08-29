package team.pre004.stackoverflowclone.dto.post;

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

    public QuestionComment toEntity(){
        return QuestionComment.builder()
                .body(body)
                .question(question)
                .build();
    }

    @Builder
    public QuestionCommentDto(String body, Question question) {
        this.body = body;
        this.question = question;
    }
}
