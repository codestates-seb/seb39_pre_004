package team.pre004.stackoverflowclone.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @GetMapping("/list")
    public ResponseEntity getQuestionsList(){

        Map<String, String> map = new HashMap<>();
        map.put("body", "getQuestionsList");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity getQuestion(){

        Map<String, String> map = new HashMap<>();
        map.put("body", "getQuestion");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PostMapping("/write")
    public ResponseEntity postQuestion(){

        Map<String, String> map = new HashMap<>();
        map.put("body", "postQuestion");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @DeleteMapping("/write")
    public ResponseEntity deleteQuestion(){

        Map<String, String> map = new HashMap<>();
        map.put("body", "deleteQuestion");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/write/{question_id}")
    public ResponseEntity putQuestion(@PathVariable Long question_id ){

        Map<String, String> map = new HashMap<>();
        map.put("body", "putQuestion");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("/comment/{question_id}")
    public ResponseEntity getComment(@PathVariable Long question_id){

        Map<String, String> map = new HashMap<>();
        map.put("body", "getComment");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PostMapping("/comment/{question_id}")
    public ResponseEntity postComment(@PathVariable Long question_id){

        Map<String, String> map = new HashMap<>();
        map.put("body", "postComment");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/comment/{question_id}")
    public ResponseEntity putComment(@PathVariable Long question_id){

        Map<String, String> map = new HashMap<>();
        map.put("body", "putComment");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{question_id}")
    public ResponseEntity deleteComment(@PathVariable Long question_id){

        Map<String, String> map = new HashMap<>();
        map.put("body", "deleteComment");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }


}
