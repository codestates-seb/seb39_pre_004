package team.pre004.stackoverflowclone.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @GetMapping("/") //메인 페이지 (게시글들 전체 조회)
    public ResponseEntity getQuestions() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/add") // 게시글 작성 페이지
    public ResponseEntity getAddQuestionForm() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add") //게시글 작성 요청
    public ResponseEntity addQuestion() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}") // 게시글 조회 페이지
    public ResponseEntity getQuestion(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}/edit") // 게시글 수정 페이지
    public ResponseEntity getEditQuestionForm(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}/edit") // 게시글 수정 요청
    public ResponseEntity updateQuestion(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // 게시글 삭제 요청
    public ResponseEntity deleteQuestion(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-up") // 게시글 좋아요 요청
    public ResponseEntity upQuestionLike(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-down") // 게시글 싫어요 요청
    public ResponseEntity downQuestionLike(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-up/undo") // 게시글 좋아요 요청 취소
    public ResponseEntity upUndoQuestionLike(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-down/undo") // 게시글 싫어요 요청
    public ResponseEntity downUndoQuestionLike(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/comments") //게시글 댓글 작성 요청
    public ResponseEntity addQuestionComment(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}/comments/{commentId}") //게시글 댓글 수정 요청
    public ResponseEntity updateQuestionComment(@PathVariable Long id, @PathVariable Long commentId) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/comments/{commentId}") //게시글 댓글 삭제 요청
    public ResponseEntity deleteQuestionComment(@PathVariable Long id, @PathVariable Long commentId) {

        return new ResponseEntity(HttpStatus.OK);
    }


}
