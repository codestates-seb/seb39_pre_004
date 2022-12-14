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

    @GetMapping("/add") // ????????? ?????? ?????????
    public ResponseEntity<?> getAddQuestionForm() {

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("????????? ?????? ??????????????????.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/add") //????????? ?????? ??????
    public ResponseEntity<?> addQuestion(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @RequestBody QuestionPostDto questionPostDto
    ) {

        //Todo : ???????????? ????????? ?????? ????????? ??? ??? ????????????.
        //Todo : ????????? title, body, tags??? DB??? ???????????????.

        Question question = questionService.save(
                questionMapper.questionPostDtoToQuestion(principalDetails.getOwner(), questionPostDto)
        );

        //Todo : ????????? ?????? ???????????? ?????? ???????????? ?????????????????? ?????????.
        return new ResponseEntity<>(question.getQuestionId(), HttpStatus.OK);
    }

    @GetMapping("/{questionId}") // ????????? ?????? ?????????
    public ResponseEntity<?> getQuestion(@PathVariable Long questionId) {

        questionService.updateView(questionId); //
        Question question = questionService.findById(questionId).orElseThrow();

        QuestionInfoDto questionInfo = questionMapper.getQuestionInfo(question);

        QuestionRespDto<?> response = QuestionRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .question(questionInfo)
                .message("????????? ?????? ??????????????????.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{questionId}/edit") // ????????? ?????? ?????????
    public ResponseEntity<?> getEditQuestionForm(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                 @PathVariable Long questionId) {

        Long userId = principalDetails.getOwner().getOwnerId();
        Question question = questionService.findById(questionId).orElseThrow(
                () -> new CustomNotContentByIdException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );

        authService.isAuthenticatedUser(userId, question.getOwner().getOwnerId());

        QuestionRespDto<?> response = QuestionRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("????????? ?????? ????????? ?????????.")
                .question(questionMapper.getQuestionPostDto(question))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{questionId}/edit") // ????????? ?????? ??????
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

    @DeleteMapping("/{questionId}") // ????????? ?????? ??????
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

    @PostMapping("/{questionId}/likes-up") // ????????? ????????? ??????
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

    @PostMapping("/{questionId}/likes-down") // ????????? ????????? ??????
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

    @PostMapping("/{questionId}/likes-up/undo") // ????????? ????????? ?????? ??????
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

    @PostMapping("/{questionId}/likes-down/undo") // ????????? ????????? ?????? ??????
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

    @PostMapping("/{questionId}/comments")//????????? ?????? ?????? ??????
    @Transactional
    public ResponseEntity<?> addQuestionComment(@PathVariable Long questionId,
                                                @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                @RequestBody QuestionCommentDto questionCommentDto) {
        //Todo : ???????????? ????????? ????????? ?????? ??? ??? ????????????.
        //Todo : ????????? ????????? DB??? ???????????????.

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

    @PutMapping("/{questionId}/comments/{commentId}") //????????? ?????? ?????? ??????
    @Transactional
    public ResponseEntity<?> updateQuestionComment(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long questionId,
            @PathVariable Long commentId,
            @RequestBody QuestionCommentDto questionCommentDto) {

        //Todo : ?????? ???????????? ????????? ??? ????????????.

        //Todo : ?????? ????????? ????????? ???????????????.

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

    @DeleteMapping("/{questionId}/comments/{commentId}") //????????? ?????? ?????? ??????
    @Transactional
    public ResponseEntity<?> deleteQuestionComment(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long questionId,
            @PathVariable Long commentId) {

        //Todo : ?????? ???????????? ????????? ??? ????????????.

        //Todo : ?????? ????????? ????????? ???????????????.
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
