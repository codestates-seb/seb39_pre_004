package team.pre004.stackoverflowclone.dto.post;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import team.pre004.stackoverflowclone.domain.tag.entity.TagList;


import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter(AccessLevel.NONE)
public class QuestionDto {

    private final Long questionId;

    private String title;
    private String body;

    private int views;

    private int downLikeCount;
    private int upLikeCount;

    private String link;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

    private List<AnswerDto> answers;
    private List<CommentDto> comments;
    private List<TagList> tagLists;


    //public Page<QuestionDto> toQuestionPage(List<Question> questions,  )
}
