package team.pre004.stackoverflowclone.dto.post.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import team.pre004.stackoverflowclone.domain.tag.Tag;

import java.util.List;

@Data
@Setter(AccessLevel.NONE)
public class RequestQuestionDto {

    private String title;
    private String body;
    private List<Tag> tags;

}
