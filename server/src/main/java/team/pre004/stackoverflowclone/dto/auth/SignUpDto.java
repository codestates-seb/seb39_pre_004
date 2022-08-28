package team.pre004.stackoverflowclone.dto.auth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

@Data
@Setter(AccessLevel.NONE)
public class SignUpDto {

    private String name;
    private final String email;
    private String password;

    @Builder
    public SignUpDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Users toUsers(){
        return Users.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
