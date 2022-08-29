package team.pre004.stackoverflowclone.dto.post;

import lombok.*;

import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionLikeUp;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.domain.user.entity.Users;


import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionDto {

    private Users owner;
    private Long questionId;

    private String title;
    private String body;

    private int views;

    private List<TagList> tags;

    private String link;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

    private List<AnswerDto> answers;
    private List<CommentDto> comments;

    @Builder
    public QuestionDto(Long questionId, String title, String body, int views, List<TagList> tags, String link, List<AnswerDto> answers, List<CommentDto> comments) {
        this.questionId = questionId;
        this.title = title;
        this.body = body;
        this.views = views;
        this.tags = tags;
        this.link = link;
        this.answers = answers;
        this.comments = comments;
    }

    public Question toEntity(){
        return Question.builder()
                .owner(owner)
                .title(title)
                .body(body)
                .tags(tags)
                .build();
    }


    //public Page<QuestionDto> toQuestionPage(List<Question> questions,  )
}
