package team.pre004.stackoverflowclone.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/{username_id}")
    public ResponseEntity getUserName(@PathVariable String username_id){

        Map<String, String> map = new HashMap<>();
        map.put("body", "getUserName");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity putUserForm(){

        Map<String, String> map = new HashMap<>();
        map.put("body", "putUserForm");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
