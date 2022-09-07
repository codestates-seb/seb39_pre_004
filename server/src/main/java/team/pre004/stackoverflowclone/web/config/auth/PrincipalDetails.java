package team.pre004.stackoverflowclone.web.config.auth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

@Data
public class PrincipalDetails{

    private static final long serialVersionUID = 1L;

    private Users users;

    @Builder
    public PrincipalDetails(Users users){
        this.users = users;
    }

}
