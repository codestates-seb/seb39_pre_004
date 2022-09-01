package team.pre004.stackoverflowclone.dto.auth;

import lombok.*;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

@Data
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

    public Users toUsers(){
        return Users.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
