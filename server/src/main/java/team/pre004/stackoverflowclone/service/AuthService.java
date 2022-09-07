package team.pre004.stackoverflowclone.service;

import team.pre004.stackoverflowclone.dto.auth.LogInDto;
import team.pre004.stackoverflowclone.dto.auth.SignUpDto;

public interface AuthService {
    void signUp(SignUpDto signUpDto);
    void login(LogInDto logInDto);
    boolean isAuthenticatedUser(Long ownerId, Long postId);

}
