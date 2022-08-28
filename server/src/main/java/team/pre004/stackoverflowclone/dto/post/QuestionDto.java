package team.pre004.stackoverflowclone.dto.post;

import team.pre004.stackoverflowclone.dto.data.Owner;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {

    private Owner owner;

    private Long questionId;

    private String title;
    private String body;

    private int downLikeCount;
    private int upLikeCount;

    private String link;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

    private List<AnswerDto> answers;
    private List<CommentDto> commets;


}
