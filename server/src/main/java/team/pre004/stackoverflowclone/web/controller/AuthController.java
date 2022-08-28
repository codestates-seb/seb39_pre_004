package team.pre004.stackoverflowclone.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.pre004.stackoverflowclone.domain.user.Users;
import team.pre004.stackoverflowclone.domain.user.UsersRepository;
import team.pre004.stackoverflowclone.dto.auth.SignUpDto;
import team.pre004.stackoverflowclone.mapper.AuthMapper;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AuthController {

    private final AuthMapper authMapper;
    private final UsersRepository usersRepository;




    @GetMapping("/signup") //회원 가입 페이지
    public ResponseEntity getSignUpForm() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/signup") //회원 가입 요청
    public ResponseEntity signUp(@RequestBody SignUpDto signUpDto) {

        Users users = usersRepository.save(authMapper.SignUpDtoToUsers(signUpDto));
        System.out.println(users);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/signup/oauth2/authorization/google") //구글 회원 가입 페이지
    public ResponseEntity getGoogleSignUpForm() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/signup/oauth2/authorization/github") //깃허브 회원 가입 페이지
    public ResponseEntity getGithubSignUpForm() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/signup/oauth2/authorization/facebook") //페이스북 회원 가입 페이지
    public ResponseEntity getFacebookSignUpForm() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/login") //로그인 페이지
    public ResponseEntity getLoginForm() {

        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/login") //로그인요청
    public ResponseEntity login() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/login/oauth2/authorization/google") // 구글 로그인 페이지
    public ResponseEntity getGoogleLoginForm() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/login/oauth2/authorization/github") // 깃허브 로그인 페이지
    public ResponseEntity getGithubLoginForm() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/login/oauth2/authorization/facebook") // 페이스북 로그인 페이지
    public ResponseEntity getFacebookLoginForm() {

        return new ResponseEntity(HttpStatus.OK);
    }


}
