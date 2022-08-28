package team.pre004.stackoverflowclone.dto.post;

import team.pre004.stackoverflowclone.dto.common.Owner;

import java.time.LocalDateTime;

public class CommentDto {

    private Owner owner;

    private String postType;
    private Long commentId;
    private Long postId;

    private String body;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

}
