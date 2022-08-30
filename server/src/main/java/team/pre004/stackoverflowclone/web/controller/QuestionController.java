package team.pre004.stackoverflowclone.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.dto.common.CMRespDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.ResponseCode;
import team.pre004.stackoverflowclone.dto.post.response.LikesDto;
import team.pre004.stackoverflowclone.dto.post.PostType;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;
import team.pre004.stackoverflowclone.dto.post.request.QuestionDto;

import team.pre004.stackoverflowclone.handler.exception.CustomNotAccessItemsException;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointItemsExeption;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.mapper.QuestionMapper;
import team.pre004.stackoverflowclone.service.QuestionCommentService;
import team.pre004.stackoverflowclone.service.QuestionService;
import team.pre004.stackoverflowclone.web.config.auth.PrincipalDetails;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/questions")
public class QuestionController {

    //Test User
    Users users = Users.builder()
            .password("1234")
            .name("ward")
            .email("ward@ward.com")
            .bio("와드입니다")
            .build();

    private final QuestionService questionService;
    private final QuestionCommentService questionCommentService;
    private final QuestionMapper questionMapper;
    private final UsersRepository usersRepository;
    private final CommentMapper commentMapper;

    @GetMapping("/add") // 게시글 작성 페이지
    public ResponseEntity<?> getAddQuestionForm() {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add") //게시글 작성 요청
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDto questionDto) {

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        Question question = questionService.save(
                questionMapper.questionDtoToQuestion(
                        principalDetails.getUsers(), questionDto)
        );

        CMRespDto<?> reponse = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(questionMapper.getQuestionInfo(question))
                .build();


        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") // 게시글 조회 페이지
    public ResponseEntity<?> getQuestion(@PathVariable Long id) {

        questionService.updateView(id); //

        Question question = questionService.findById(id).orElseThrow();

        CMRespDto<?> reponse = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(questionMapper.getQuestionInfo(question))
                .build();

        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @GetMapping("/{id}/edit") // 게시글 수정 페이지
    public ResponseEntity<?> getEditQuestionForm(@PathVariable Long id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/edit") // 게시글 수정 요청
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto) {

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        if (questionService.findById(id).isEmpty()) //해당 게시물이 없을 때 에러메세지
            throw new CustomNullPointItemsExeption(ExceptionMessage.NOT_CONTENT_QUESTION_ID);

        if (principalDetails.getUsers().getId() != id) //접근 유저가 아닐경우
            throw new CustomNotAccessItemsException(ExceptionMessage.NOT_ACCESS_EDIT_QUESTION);


        Question question = questionService.update(id, questionMapper.questionDtoToQuestion(principalDetails.getUsers(), questionDto));

        CMRespDto<?> reponse = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(questionMapper.getQuestionInfo(question))
                .build();


        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // 게시글 삭제 요청
    public ResponseEntity deleteQuestion(@PathVariable Long id) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-up") // 게시글 좋아요 요청
    public ResponseEntity<?> upQuestionLike(@PathVariable Long id) {
        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeUp(userId, id))
                .postId(id)
                .postType(PostType.QUESTION)
                .build();

        CMRespDto<?> cmRespDto = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(likes)
                .build();
        return new ResponseEntity<>(cmRespDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-down") // 게시글 싫어요 요청
    public ResponseEntity downQuestionLike(@PathVariable Long id) {

        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeDown(userId, id))
                .postId(id)
                .postType(PostType.QUESTION)
                .build();

        CMRespDto<?> cmRespDto = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(likes)
                .build();

        return new ResponseEntity(cmRespDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-up/undo") // 게시글 좋아요 요청 취소
    public ResponseEntity upUndoQuestionLike(@PathVariable Long id) {

        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeUp(userId, id))
                .postId(id)
                .postType(PostType.QUESTION)
                .build();

        CMRespDto<?> cmRespDto = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(likes)
                .build();

        return new ResponseEntity(cmRespDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-down/undo") // 게시글 싫어요 요청 취소
    public ResponseEntity downUndoQuestionLike(@PathVariable Long id) {

        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeDown(userId, id))
                .postId(id)
                .postType(PostType.QUESTION)
                .build();

        CMRespDto<?> cmRespDto = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(likes)
                .build();

        return new ResponseEntity(cmRespDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/comments")//게시글 댓글 작성 요청
    public ResponseEntity<?> addQuestionComment(@PathVariable Long id, @RequestBody QuestionCommentDto questionCommentDto) {

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();



        QuestionComment comment =
               questionCommentService.save(
                       commentMapper.getQuestionComment(principalDetails.getUsers(), id, questionCommentDto)
               );

        CMRespDto<?> cmRespDto = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(commentMapper.getQuestionCommentInfo(comment))
                .build();

        return new ResponseEntity<>(cmRespDto, HttpStatus.OK);
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
