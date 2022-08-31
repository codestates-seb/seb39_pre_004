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

    private String createDate;
    private String modDate;

    private Set<AnswerDto> answers;
    private Set<QuestionCommentDto> comments;


}
