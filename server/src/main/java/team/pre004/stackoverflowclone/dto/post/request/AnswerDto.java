package team.pre004.stackoverflowclone.dto.post.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerDto {

    private Users owner;
    private Long id;
    private String body;
    private List<TagList> tags;
    private boolean isAccepted;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

    private Set<QuestionCommentDto> comments;
}
