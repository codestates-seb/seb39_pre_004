package team.pre004.stackoverflowclone.dto.post;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import team.pre004.stackoverflowclone.domain.post.entity.Question;

import java.util.List;

@Data
@Setter(AccessLevel.NONE)
public class TagDto {

    private String name;

}
