package team.pre004.stackoverflowclone.dto.request;

import lombok.Getter;
import team.pre004.stackoverflowclone.domain.tag.Tag;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class QuestionPostDto {

    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String body;

    private List<Tag> tagList;

}
