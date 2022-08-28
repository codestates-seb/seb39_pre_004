package team.pre004.stackoverflowclone.dto.post;

import team.pre004.stackoverflowclone.dto.data.Owner;

import java.time.LocalDateTime;
import java.util.List;

public class AnswerDto {

    private Owner owner;

    private Long answerId;
    private Long questionId;

    private String body;

    private boolean isAccepted;

    private int downLikeCount;
    private int upLikeCount;

    private String link;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

    private List<CommentDto> commets;



}
