package team.pre004.stackoverflowclone.dto.auth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class SignUpDto {

    private String name;
    private String email;
    private String password;

    @Builder
    public SignUpDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
