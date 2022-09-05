package team.pre004.stackoverflowclone.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;
import team.pre004.stackoverflowclone.dto.auth.SignUpDto;
import team.pre004.stackoverflowclone.dto.common.OwnerRespDto;
import team.pre004.stackoverflowclone.handler.ResponseCode;
import team.pre004.stackoverflowclone.service.AuthService;
import team.pre004.stackoverflowclone.security.PrincipalDetails;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> join(@RequestBody SignUpDto signUpDto){

        authService.signUp(signUpDto);

        OwnerRespDto<?> respDto = OwnerRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("회원가입을 성공하였습니다.")
                .build();

        return new ResponseEntity<>(respDto,HttpStatus.ACCEPTED);

    }

    @GetMapping("/users/oauth")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
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
