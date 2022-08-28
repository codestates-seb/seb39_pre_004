package team.pre004.stackoverflowclone.dto.response;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
public class UserAccountDto {

    private final Long userId;
    private String image;
    private String name;
    private String email;
    private String password;
    private String bio;
    private final String link;

    @Builder
    public UserAccountDto(Long userId, String image, String name, String email, String password,  String bio, String link) {
        this.userId = userId;
        this.image = image;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.link = link;
    }
}
