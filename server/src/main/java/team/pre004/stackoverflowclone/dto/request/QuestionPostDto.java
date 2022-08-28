package team.pre004.stackoverflowclone.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import team.pre004.stackoverflowclone.domain.tag.entity.Tag;

import java.util.List;

@Data
@Setter(AccessLevel.NONE)
public class QuestionPostDto {

    private Long ownerId;
    private String title;
    private String body;
    private List<Tag> tags;

}
