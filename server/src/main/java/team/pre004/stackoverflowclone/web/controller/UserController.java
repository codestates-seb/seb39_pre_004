package team.pre004.stackoverflowclone.web.controller;

import io.swagger.annotations.Api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@Api(tags = {"유저관리 API"})
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}/me") //내 정보 페이지
    public ResponseEntity getAccountForm(@PathVariable Long id){

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}") //해당 회원 조회 페이지
    public ResponseEntity getUserForm(@PathVariable Long id){

        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/{id}/edit") // 내 정보 수정 페이지
    public ResponseEntity getEditAccountForm(@PathVariable Long id){

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping ("/{id}/me/edit") // 내 정보 수정 요청
    public ResponseEntity updateAccount(@PathVariable Long id){

        return new ResponseEntity(HttpStatus.OK);
    }

}
