package team.pre004.stackoverflowclone.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity postSignUp(){

        Map<String, String> map = new HashMap<>();
        map.put("body", "postSignUp");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
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
