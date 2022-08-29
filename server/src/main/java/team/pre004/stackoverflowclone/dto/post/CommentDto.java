package team.pre004.stackoverflowclone.dto.post;

import java.time.LocalDateTime;

public class CommentDto {

    private String postType;
    private Long commentId;
    private Long postId;

    private String body;

    private LocalDateTime createDate;
    private LocalDateTime modDate;

}
