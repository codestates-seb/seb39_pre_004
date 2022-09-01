package team.pre004.stackoverflowclone.dto.post.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.dto.post.response.UserInfoDto;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionPostDto {
    UserInfoDto owner;
    private Long questionId;
    private String title;
    private String body;
    private List<TagList> tags;
    private String link;
}
