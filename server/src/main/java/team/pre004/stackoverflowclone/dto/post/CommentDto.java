package team.pre004.stackoverflowclone.dto.post;

import team.pre004.stackoverflowclone.dto.data.Owner;

import java.time.LocalDateTime;

public class CommentDto {

    private Owner owner;

    private Long commentId;
    private Long answerId;
    private Long questionId;

    private String postType;

    private String body;

    private String link;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

}
