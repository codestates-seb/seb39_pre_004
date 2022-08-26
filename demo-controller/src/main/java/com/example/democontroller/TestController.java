package com.example.democontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity hello () {

        Map<String, String> testBody = new HashMap<>();

        testBody.put("body", "hello");

        return new ResponseEntity(testBody,HttpStatus.ACCEPTED);
    }


}
