package team.pre004.stackoverflowclone.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.dto.auth.SignUpDto;
import team.pre004.stackoverflowclone.security.PrincipalDetails;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AuthController {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public String join(@RequestBody Users users){
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setRoles("ROLE_ADMIN");
        usersRepository.save(users);
        return "회원가입 완료";

    }

    @GetMapping("/users/oauth")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(principalDetails.getUsers());
        return "users";
    }




    @GetMapping("/signup") //회원 가입 페이지
    public ResponseEntity getSignUpForm() {

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
