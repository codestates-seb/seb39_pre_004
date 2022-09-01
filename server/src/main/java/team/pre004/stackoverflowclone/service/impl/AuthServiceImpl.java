package team.pre004.stackoverflowclone.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.pre004.stackoverflowclone.domain.user.Role;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.dto.auth.LogInDto;
import team.pre004.stackoverflowclone.dto.auth.SignUpDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomDuplicationUsersException;
import team.pre004.stackoverflowclone.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    @Transactional
    public void signUp(SignUpDto signUpDto) {

        usersRepository.findByName(signUpDto.getName()).ifPresent(
                users -> {
                    throw new CustomDuplicationUsersException(ExceptionMessage.CONFLICT_USER_NAME);
                }
        );

        usersRepository.findByEmail(signUpDto.getEmail()).ifPresent(
                users -> {
                    throw new CustomDuplicationUsersException(ExceptionMessage.CONFLICT_USER_EMAIL);
                }
        );

        Users users = Users.builder()
                .name(signUpDto.getName())
                .password(bCryptPasswordEncoder.encode(signUpDto.getPassword()))
                .email(signUpDto.getEmail())
                .roles(Role.ADMIN)
                .build();

        usersRepository.saveAndFlush(users);
    }

    @Override
    public void login(LogInDto logInDto) {

    }
}
