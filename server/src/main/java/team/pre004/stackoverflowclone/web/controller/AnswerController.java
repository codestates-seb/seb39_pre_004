package team.pre004.stackoverflowclone.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswerController {


    @PostMapping("/add") // 답글 작성 요청
    public ResponseEntity addAnswer() {

        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/{id}/edit") // 답글 수정 요청
    public ResponseEntity editAnswer(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // 답글 삭제 요청
    public ResponseEntity deleteAnswer(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-up") // 답글 좋아요 요청
    public ResponseEntity upAnswerLike(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-down") // 답글 싫어요 요청
    public ResponseEntity downAnswerLike(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-up/undo") // 답글글 좋아요 요청취소
    public ResponseEntity upUndoAnswerLike(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-down/undo") // 답글 싫어요 요청 취소
    public ResponseEntity downUndoAnswerLike(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/comments") //답글 댓글 작성 요청
    public ResponseEntity addAnswerComment(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}/comments/{commentId}") //답글 댓글 수정 요청
    public ResponseEntity updateAnswerComment(@PathVariable Long id, @PathVariable Long commentId) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/comments/{commentId}") //답글 댓글 삭제 요청
    public ResponseEntity deleteAnswerComment(@PathVariable Long id, @PathVariable Long commentId) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/selected") // 답글 채택 요청
    public ResponseEntity selectAnswer(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/selected/undo") //답글 채택 취소 요청
    public ResponseEntity undoSelectAnswer(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }







}
