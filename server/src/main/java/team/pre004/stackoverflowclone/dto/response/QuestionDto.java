package team.pre004.stackoverflowclone.dto.response;

import lombok.Data;
import team.pre004.stackoverflowclone.dto.AnswerDto;
import team.pre004.stackoverflowclone.dto.common.Owner;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionDto {

    private final Owner owner;

    private final Long id;

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
