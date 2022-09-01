package team.pre004.stackoverflowclone.dto.post.response;

import lombok.*;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QuestionIndexDto {

    UserInfoDto owner;
    private Long questionId;

    private String title;
    private String body;
    private Integer likes;
    private int views;
    private boolean isAccepted;
    private List<TagList> tags;
    private String createDate;
    private String modDate;

    private String link;
    private int answers;
}
