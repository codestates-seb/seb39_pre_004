package team.pre004.stackoverflowclone.dto.post.response;

import lombok.Builder;
import lombok.Data;

@Data
public class UserInfoDto {

    private Long userId;
    private String name;
    private String email;
    private String image;
    private String bio;
    private String link;

    @Builder
    public UserInfoDto(Long userId, String name, String email, String image, String bio, String link) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.image = image;
        this.bio = bio;
        this.link = link;
    }
}
