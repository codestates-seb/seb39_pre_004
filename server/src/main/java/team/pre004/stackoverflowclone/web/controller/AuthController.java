package team.pre004.stackoverflowclone.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.pre004.stackoverflowclone.domain.user.User;
import team.pre004.stackoverflowclone.domain.user.UserRepository;
import team.pre004.stackoverflowclone.dto.request.UserSignUpDto;
import team.pre004.stackoverflowclone.dto.response.UserResponseDto;
import team.pre004.stackoverflowclone.mapper.AuthMapper;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@Api(tags = {"유저관리 API"})
@RequestMapping("/users")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public ResponseEntity postSignUp(@Valid @RequestBody UserSignUpDto requestbody){

        User user = userRepository.save(authMapper.userSignUpToUser(requestbody));
        UserResponseDto response = authMapper.userToUserResponseDto(userRepository.getReferenceById(user.getId()));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/auth/google")
    public ResponseEntity postSignUpGoogle(){

        Map<String, String> map = new HashMap<>();
        map.put("body", "postSignUpGoogle");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity postLogin(){

        Map<String, String> map = new HashMap<>();
        map.put("body", "postLogin");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PostMapping("/login/auth/google")
    public ResponseEntity postLoginGoogle(){

        Map<String, String> map = new HashMap<>();
        map.put("body", "postLoginGoogle");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PostMapping("/findpw")
    public ResponseEntity postFindPw(){

        Map<String, String> map = new HashMap<>();
        map.put("body", "postFindPw");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity postLogout(){

        Map<String, String> map = new HashMap<>();
        map.put("body", "postLogout");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

}
