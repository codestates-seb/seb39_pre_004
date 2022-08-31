package team.pre004.stackoverflowclone.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.dto.common.CMRespDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionInfoDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.ResponseCode;
import team.pre004.stackoverflowclone.dto.post.response.LikesDto;
import team.pre004.stackoverflowclone.dto.post.PostType;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;
import team.pre004.stackoverflowclone.dto.post.request.QuestionDto;

import team.pre004.stackoverflowclone.handler.exception.CustomNotAccessItemsException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.mapper.QuestionMapper;
import team.pre004.stackoverflowclone.service.CommonService;
import team.pre004.stackoverflowclone.service.QuestionCommentService;
import team.pre004.stackoverflowclone.service.QuestionService;
import team.pre004.stackoverflowclone.web.config.auth.PrincipalDetails;


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

    private final CommonService commonService;

    @ApiOperation( value = "질문 작성 페이지", notes = "질문을 작성 할 수 있는 페이지로 이동합니다.")
    @GetMapping("/add") // 게시글 작성 페이지
    public ResponseEntity<?> getAddQuestionForm() {

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("게시글 작성성 페이지입니다")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation( value = "<요청> 질문 작성 요청", notes = "질문 작성 페이지에서 양식 작성 후 해당 질문을 게시합니다.")
    @PostMapping("/add") //게시글 작성 요청
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDto questionDto) {

        //Todo : 로그인한 유저만 작성 요청을 할 수 있습니다.

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        //Todo : 작성한 title, body, tags를 DB에 저장합니다.

        Question question = questionService.save(
                questionMapper.questionDtoToQuestion(
                        principalDetails.getUsers(), questionDto)
        );
        //Todo : 작성한 질문 아이디의 조회 페이지로 리다이렉션을 합니다.
        return new ResponseEntity<>(commonService.redirect("/questions/" + question.getQuestionId()), HttpStatus.MOVED_PERMANENTLY);
    }
    @ApiOperation( value = "질문 조회 페이지", notes = "요청한 id의 질문을 조회합니다.")
    @GetMapping("/{questionId}") // 게시글 조회 페이지
    public ResponseEntity<?> getQuestion(@PathVariable Long questionId) {

        questionService.updateView(questionId); //

        Question question = questionService.findById(questionId).orElseThrow();

        QuestionInfoDto questionInfo = questionMapper.getQuestionInfo(question);

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(questionInfo)
                .message("게시글 조회 페이지입니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation( value = "질문 수정 페이지", notes = "질문을 수정 할 수 있는 페이지로 이동합니다.")
    @GetMapping("/{questionId}/edit") // 게시글 수정 페이지
    public ResponseEntity<?> getEditQuestionForm(@PathVariable Long questionId) {

        Question question = questionService.findById(questionId).orElseThrow();

        QuestionInfoDto questionInfo = questionMapper.getQuestionInfo(question);
        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("게시글 수정 페이지 입니다.")
                .data(questionInfo)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation( value = "<요청> 질문 수정 요청", notes = "양식에 맞춰 수정한 질문을 저장합니다.")
    @PutMapping("/{questionId}/edit") // 게시글 수정 요청
    public ResponseEntity<?> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionDto questionDto) {

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        if (questionService.findById(questionId).isEmpty()) //해당 게시물이 없을 때 에러메세지
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID);

        if (principalDetails.getUsers().getOwnerId() != questionId) //접근 유저가 아닐경우
            throw new CustomNotAccessItemsException(ExceptionMessage.NOT_ACCESS_EDIT_QUESTION);

        Question question = questionService.update(questionId, questionMapper.questionDtoToQuestion(principalDetails.getUsers(), questionDto));


        return new ResponseEntity<>(commonService.redirect("/questions/" + question.getQuestionId()), HttpStatus.MOVED_PERMANENTLY);
    }

    @ApiOperation( value = "<요청> 질문 삭제 요청", notes = "요청한 id의 질문을 삭제합니다.")
    @DeleteMapping("/{questionId}") // 게시글 삭제 요청
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {

        questionService.deleteById(questionId);

        return new ResponseEntity<>(commonService.redirect("/"), HttpStatus.MOVED_PERMANENTLY);
    }

    @ApiOperation( value = "<요청> 질문 좋아요 요청", notes = "좋아요 버튼을 누르면 해당 질문의 좋아요가 1올라가고, 좋아요를 누른 회원 정보에 저장됩니다.")
    @PostMapping("/{questionId}/likes-up") // 게시글 좋아요 요청
    public ResponseEntity<?> upQuestionLike(@PathVariable Long questionId) {
        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeUp(userId, questionId))
                .postId(questionId)
                .postType(PostType.QUESTION)
                .build();

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(likes)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-down") // 게시글 싫어요 요청
    public ResponseEntity<?> downQuestionLike(@PathVariable Long id) {

        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeDown(userId, id))
                .postId(id)
                .postType(PostType.QUESTION)
                .build();

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(likes)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-up/undo") // 게시글 좋아요 요청 취소
    public ResponseEntity<?> upUndoQuestionLike(@PathVariable Long id) {

        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeUpUndo(userId, id))
                .postId(id)
                .postType(PostType.QUESTION)
                .build();

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(likes)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-down/undo") // 게시글 싫어요 요청 취소
    public ResponseEntity<?> downUndoQuestionLike(@PathVariable Long id) {

        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeDownUndo(userId, id))
                .postId(id)
                .postType(PostType.QUESTION)
                .build();

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(likes)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/comments")//게시글 댓글 작성 요청
    public ResponseEntity<?> addQuestionComment(@PathVariable Long id, @RequestBody QuestionCommentDto questionCommentDto) {


        //Todo : 로그인한 회원만 댓글을 작성 할 수 있습니다.


        //Todo : 작성한 댓글을 DB에 저장합니다.
        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        questionCommentService.save(
                commentMapper.getQuestionComment(principalDetails.getUsers(), id, questionCommentDto));

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(questionCommentService.findAllByQuestion(id))
                .build();

        //Todo : ?

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/comments/{commentId}") //게시글 댓글 수정 요청
    public ResponseEntity<?> updateQuestionComment(
            @PathVariable Long id, @PathVariable Long commentId,
            @RequestBody QuestionCommentDto questionCommentDto) {

        //Todo : 댓글 게시자만 수정할 수 있습니다.

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        //Todo : 해당 질문의 댓글을 수정합니다.


        questionCommentService.update(id, commentId, questionCommentDto);

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(questionCommentService.findAllByQuestion(id))
                .build();

        //Todo : ?

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/comments/{commentId}") //게시글 댓글 삭제 요청
    public ResponseEntity<?> deleteQuestionComment(@PathVariable Long id, @PathVariable Long commentId) {

        //Todo : 댓글 게시자만 삭제할 수 있습니다.

        //Todo : 해당 질문의 댓글을 삭제합니다.
        questionCommentService.deleteById(id, commentId);

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(questionCommentService.findAllByQuestion(id))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
