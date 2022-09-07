package team.pre004.stackoverflowclone.dto.post.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.dto.post.PostType;


@Data
@NoArgsConstructor
public class LikesDto {
    private Integer likes;
    private PostType postType;
    private Long postId;

    @Builder
    public LikesDto(Integer likes, PostType postType, Long postId) {
        this.likes = likes;
        this.postType = postType;
        this.postId = postId;
    }
}
