package team.pre004.stackoverflowclone.dto.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Owner {

    private Long userId;
    private String userImage;
    private String displayName;
    private String link;

    @Builder
    public Owner(Long userId, String userImage, String displayName, String link) {
        this.userId = userId;
        this.userImage = userImage;
        this.displayName = displayName;
        this.link = link;
    }

    public String createMyLink(Long userId){
        return "/users/" + userId.toString();
    }

}
