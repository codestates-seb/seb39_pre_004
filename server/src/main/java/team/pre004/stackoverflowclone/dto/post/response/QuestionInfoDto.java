package team.pre004.stackoverflowclone.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;
import team.pre004.stackoverflowclone.dto.post.request.AnswerDto;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionInfoDto {

    UserInfoDto owner;

    private Long questionId;

    private String title;
    private String body;
    private Integer likes;
    private int views;
    private boolean isAccepted;
    private List<TagList> tags;
    private String link;
    private LocalDateTime createDate;
    private LocalDateTime modDate;
    private Set<AnswerInfoDto> answers;
    private Set<QuestionCommentInfoDto> comments;

}
