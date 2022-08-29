package team.pre004.stackoverflowclone.dto.post.request;

import lombok.*;

import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.domain.user.entity.Users;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionDto {

    private Users owner;
    private Long id;

    private String title;
    private String body;

    private int views;

    private List<TagList> tags;

    private String link;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

    private Set<AnswerDto> answers;
    private Set<QuestionCommentDto> comments;

    @Builder
    public QuestionDto(Long id, String title, String body, int views, List<TagList> tags, String link, Set<AnswerDto> answers, Set<QuestionCommentDto> comments) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.views = views;
        this.tags = tags;
        this.link = link;
        this.answers = answers;
        this.comments = comments;
    }


}
