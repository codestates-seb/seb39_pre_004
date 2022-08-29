package team.pre004.stackoverflowclone.dto.post;



import java.time.LocalDateTime;
import java.util.List;

public class AnswerDto {


    private Long answerId;

    private int downLikeCount;
    private int upLikeCount;

    private String body;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

    private List<QuestionCommentDto> commets;
}
