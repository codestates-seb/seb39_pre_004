package team.pre004.stackoverflowclone.web.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.dto.common.CMRespDto;
import team.pre004.stackoverflowclone.dto.common.CommentRespDto;
import team.pre004.stackoverflowclone.dto.common.LikeRespDto;
import team.pre004.stackoverflowclone.dto.common.QuestionRespDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionInfoDto;
import team.pre004.stackoverflowclone.dto.post.request.QuestionPostDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.ResponseCode;
import team.pre004.stackoverflowclone.dto.post.response.LikesDto;
import team.pre004.stackoverflowclone.dto.post.PostType;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;

import team.pre004.stackoverflowclone.handler.exception.CustomNotContentByIdException;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.mapper.QuestionMapper;
import team.pre004.stackoverflowclone.mapper.UsersMapper;
import team.pre004.stackoverflowclone.security.PrincipalDetails;
import team.pre004.stackoverflowclone.service.AuthService;
import team.pre004.stackoverflowclone.service.CommonService;
import team.pre004.stackoverflowclone.service.QuestionCommentService;
import team.pre004.stackoverflowclone.service.QuestionService;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    //Test User

    private final QuestionService questionService;
    private final QuestionCommentService questionCommentService;
    private final AuthService authService;
    private final QuestionMapper questionMapper;
    private final UsersRepository usersRepository;
    private final CommentMapper commentMapper;
    private final UsersMapper usersMapper;
    private final CommonService commonService;

    @GetMapping("/add") // 게시글 작성 페이지
    public ResponseEntity<?> getAddQuestionForm() {

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("게시글 조회 페이지입니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/add") //게시글 작성 요청
    public ResponseEntity<?> addQuestion(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @RequestBody QuestionPostDto questionPostDto
    ) {
        log.info(principalDetails.getUsername());
        log.info(questionPostDto.toString());
        //Todo : 로그인한 유저만 작성 요청을 할 수 있습니다.
        //Todo : 작성한 title, body, tags를 DB에 저장합니다.

        Question question = questionService.save(
                questionMapper.questionPostDtoToQuestion(principalDetails.getOwner(), questionPostDto)
        );

        //Todo : 작성한 질문 아이디의 조회 페이지로 리다이렉션을 합니다.
        return new ResponseEntity<>(question.getQuestionId(), HttpStatus.OK);
    }

    @GetMapping("/{questionId}") // 게시글 조회 페이지
    public ResponseEntity<?> getQuestion(@PathVariable Long questionId) {

        questionService.updateView(questionId); //
        Question question = questionService.findById(questionId).orElseThrow();

        QuestionInfoDto questionInfo = questionMapper.getQuestionInfo(question);

        QuestionRespDto<?> response = QuestionRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .question(questionInfo)
                .message("게시글 조회 페이지입니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{questionId}/edit") // 게시글 수정 페이지
    public ResponseEntity<?> getEditQuestionForm(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                 @PathVariable Long questionId) {

        Long userId = principalDetails.getOwner().getOwnerId();
        Question question = questionService.findById(questionId).orElseThrow(
                () -> new CustomNotContentByIdException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );

        authService.isAuthenticatedUser(userId, question.getOwner().getOwnerId());

        QuestionRespDto<?> response = QuestionRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("게시글 수정 페이지 입니다.")
                .question(questionMapper.getQuestionPostDto(question))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{questionId}/edit") // 게시글 수정 요청
    @Transactional
    public ResponseEntity<?> updateQuestion(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                            @PathVariable Long questionId,
                                            @RequestBody QuestionPostDto questionPostDto) {

        Long userId = principalDetails.getOwner().getOwnerId();
        Question question = questionService.findById(questionId).orElseThrow(
                () -> new CustomNotContentByIdException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );

        authService.isAuthenticatedUser(userId, question.getOwner().getOwnerId());

        questionService.update(questionId, questionPostDto);

        return new ResponseEntity<>(getQuestion(question.getQuestionId()), HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}") // 게시글 삭제 요청
    @Transactional
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getOwner().getOwnerId();
        Question question = questionService.findById(questionId).orElseThrow(
                () -> new CustomNotContentByIdException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );

        authService.isAuthenticatedUser(userId, question.getOwner().getOwnerId());
        questionService.deleteById(questionId);

        return new ResponseEntity<>(commonService.redirect("/main"), HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/{questionId}/likes-up") // 게시글 좋아요 요청
    @Transactional
    public ResponseEntity<?> upQuestionLike(@PathVariable Long questionId,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getOwner().getOwnerId();

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeUp(userId, questionId))
                .postId(questionId)
                .postType(PostType.QUESTION)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .like(likes)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{questionId}/likes-down") // 게시글 싫어요 요청
    @Transactional
    public ResponseEntity<?> downQuestionLike(@PathVariable Long questionId,
                                              @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getOwner().getOwnerId();

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeDown(userId, questionId))
                .postId(questionId)
                .postType(PostType.QUESTION)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .like(likes)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{questionId}/likes-up/undo") // 게시글 좋아요 요청 취소
    @Transactional
    public ResponseEntity<?> upUndoQuestionLike(@PathVariable Long questionId,
                                                @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getOwner().getOwnerId();

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeUpUndo(userId, questionId))
                .postId(questionId)
                .postType(PostType.QUESTION)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .like(likes)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{questionId}/likes-down/undo") // 게시글 싫어요 요청 취소
    @Transactional
    public ResponseEntity<?> downUndoQuestionLike(@PathVariable Long questionId,
                                                  @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getOwner().getOwnerId();

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeDownUndo(userId, questionId))
                .postId(questionId)
                .postType(PostType.QUESTION)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .like(likes)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{questionId}/comments")//게시글 댓글 작성 요청
    @Transactional
    public ResponseEntity<?> addQuestionComment(@PathVariable Long questionId,
                                                @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                @RequestBody QuestionCommentDto questionCommentDto) {
        //Todo : 로그인한 회원만 댓글을 작성 할 수 있습니다.
        //Todo : 작성한 댓글을 DB에 저장합니다.

        Users user = principalDetails.getOwner();
        questionService.findById(questionId).orElseThrow(
                () -> new CustomNotContentByIdException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );

        questionCommentService.save(
                commentMapper.getQuestionComment(user, questionId, questionCommentDto));

        CommentRespDto<?> response = CommentRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .comment(commentMapper.getQuestionCommentInfos(questionCommentService.findAllByQuestion(questionId)))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{questionId}/comments/{commentId}") //게시글 댓글 수정 요청
    @Transactional
    public ResponseEntity<?> updateQuestionComment(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long questionId,
            @PathVariable Long commentId,
            @RequestBody QuestionCommentDto questionCommentDto) {

        //Todo : 댓글 게시자만 수정할 수 있습니다.

        //Todo : 해당 질문의 댓글을 수정합니다.

        Users user = principalDetails.getOwner();
        questionService.findById(questionId).orElseThrow(
                () -> new CustomNotContentByIdException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );

        authService.isAuthenticatedUser(user.getOwnerId(), commentId);

        questionCommentService.update(questionId, commentId, questionCommentDto);

        CommentRespDto<?> response = CommentRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .comment(commentMapper.getQuestionCommentInfos(questionCommentService.findAllByQuestion(questionId)))
                .build();

        //Todo : ?

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}/comments/{commentId}") //게시글 댓글 삭제 요청
    @Transactional
    public ResponseEntity<?> deleteQuestionComment(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long questionId,
            @PathVariable Long commentId) {

        //Todo : 댓글 게시자만 삭제할 수 있습니다.

        //Todo : 해당 질문의 댓글을 삭제합니다.
        Users user = principalDetails.getOwner();
        questionService.findById(questionId).orElseThrow(
                () -> new CustomNotContentByIdException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );
        authService.isAuthenticatedUser(user.getOwnerId(), commentId);
        questionCommentService.deleteById(questionId, commentId);

        CommentRespDto<?> response = CommentRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .comment(questionCommentService.findAllByQuestion(questionId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
