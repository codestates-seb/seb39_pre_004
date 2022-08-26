package team.pre004.stackoverflowclone.dto;


import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class UserSignUpDto {

    @NotBlank(message =  "아이디는 공백이 아니어야 합니다.")
    private String name;

    @NotBlank(message =  "이메일은 공백이 아니어야 합니다.")
    @Email(message = "이메일 양식을 지켜주세요.")
    private String email;

    @NotBlank(message =  "비밀번호는 공백이 아니어야 합니다.")
    private String password;


}
