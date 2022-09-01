package team.pre004.stackoverflowclone.web.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
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

import team.pre004.stackoverflowclone.handler.exception.CustomNotAccessItemsException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.mapper.QuestionMapper;
import team.pre004.stackoverflowclone.mapper.UsersMapper;
import team.pre004.stackoverflowclone.security.PrincipalDetails;
import team.pre004.stackoverflowclone.service.CommonService;
import team.pre004.stackoverflowclone.service.QuestionCommentService;
import team.pre004.stackoverflowclone.service.QuestionService;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/questions")
public class QuestionController {

    //Test User

    private final QuestionService questionService;
    private final QuestionCommentService questionCommentService;
    private final QuestionMapper questionMapper;
    private final UsersRepository usersRepository;
    private final CommentMapper commentMapper;
    private final UsersMapper usersMapper;
    private final CommonService commonService;

    @GetMapping("/add") // 게시글 작성 페이지
    @Transactional(readOnly = true)
    public ResponseEntity<?> getAddQuestionForm() {

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("게시글 조회 페이지입니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/add") //게시글 작성 요청
    @Transactional
    public ResponseEntity<?> addQuestion(
                                        @AuthenticationPrincipal PrincipalDetails principalDetails
                                        ,@RequestHeader("Authorization") HttpHeaders headers
                                        ,@RequestBody QuestionPostDto questionPostDto
    ) {
        //Todo : 로그인한 유저만 작성 요청을 할 수 있습니다.
        //Todo : 작성한 title, body, tags를 DB에 저장합니다.

        Question question = questionService.save(
                questionMapper.questionPostDtoToQuestion(principalDetails.getOwner(), questionPostDto)
        );

        StringBuilder sb = new StringBuilder("/questions/" + question.getQuestionId());

        //Todo : 작성한 질문 아이디의 조회 페이지로 리다이렉션을 합니다.
        return new ResponseEntity<>(commonService.redirect(sb.toString()), HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/{id}") // 게시글 조회 페이지
    @Transactional(readOnly = true)
    public ResponseEntity<?> getQuestion(@PathVariable Long id) {

        questionService.updateView(id); //
        Question question = questionService.findById(id).orElseThrow();

        QuestionInfoDto questionInfo = questionMapper.getQuestionInfo(question);

        QuestionRespDto<?> response = QuestionRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .question(questionInfo)
                .message("게시글 조회 페이지입니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/edit") // 게시글 수정 페이지
    @Transactional(readOnly = true)
    public ResponseEntity<?> getEditQuestionForm(@PathVariable Long id) {

        Question question = questionService.findById(id).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );

        QuestionPostDto questionPostDto = questionMapper.getQuestionPostDto(question);

        QuestionRespDto<?> response = QuestionRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("게시글 수정 페이지 입니다.")
                .question(questionPostDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/edit") // 게시글 수정 요청
    @Transactional
    public ResponseEntity<?> updateQuestion(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id, @RequestBody QuestionPostDto questionPostDto) {


        if (questionService.findById(id).isEmpty()) //해당 게시물이 없을 때 에러메세지
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID);

        if (principalDetails.getOwner().getOwnerId() != id) //접근 유저가 아닐경우
            throw new CustomNotAccessItemsException(ExceptionMessage.NOT_ACCESS_EDIT_QUESTION);

        Question question = questionService.update(id, questionMapper.questionPostDtoToQuestion(
                principalDetails.getOwner(), questionPostDto));

        return new ResponseEntity<>(commonService.redirect("/questions/" + question.getQuestionId()), HttpStatus.MOVED_PERMANENTLY);
    }

    @DeleteMapping("/{id}") // 게시글 삭제 요청
    @Transactional
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {

        questionService.deleteById(id);

        return new ResponseEntity<>(commonService.redirect("/"), HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/{id}/likes-up") // 게시글 좋아요 요청
    @Transactional
    public ResponseEntity<?> upQuestionLike(@PathVariable Long id) {
        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeUp(userId, id))
                .postId(id)
                .postType(PostType.QUESTION)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .like(likes)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-down") // 게시글 싫어요 요청
    @Transactional
    public ResponseEntity<?> downQuestionLike(@PathVariable Long id) {

        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeDown(userId, id))
                .postId(id)
                .postType(PostType.QUESTION)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .like(likes)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-up/undo") // 게시글 좋아요 요청 취소
    @Transactional
    public ResponseEntity<?> upUndoQuestionLike(@PathVariable Long id) {

        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeUpUndo(userId, id))
                .postId(id)
                .postType(PostType.QUESTION)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .like(likes)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/likes-down/undo") // 게시글 싫어요 요청 취소
    @Transactional
    public ResponseEntity<?> downUndoQuestionLike(@PathVariable Long id) {

        Long userId = 1L;

        LikesDto likes = LikesDto.builder()
                .likes(questionService.selectLikeDownUndo(userId, id))
                .postId(id)
                .postType(PostType.QUESTION)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .like(likes)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/comments")//게시글 댓글 작성 요청
    @Transactional
    public ResponseEntity<?> addQuestionComment(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id, @RequestBody QuestionCommentDto questionCommentDto) {


        //Todo : 로그인한 회원만 댓글을 작성 할 수 있습니다.

        //Todo : 작성한 댓글을 DB에 저장합니다.

        questionCommentService.save(
                commentMapper.getQuestionComment(principalDetails.getOwner(), id, questionCommentDto));

        CommentRespDto<?> response = CommentRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .comment(commentMapper.getQuestionCommentInfos(questionCommentService.findAllByQuestion(id)))
                .build();
        //Todo : ?

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/comments/{commentId}") //게시글 댓글 수정 요청
    @Transactional
    public ResponseEntity<?> updateQuestionComment(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long id, @PathVariable Long commentId,
            @RequestBody QuestionCommentDto questionCommentDto) {

        //Todo : 댓글 게시자만 수정할 수 있습니다.

        //Todo : 해당 질문의 댓글을 수정합니다.

        questionCommentService.update(id, commentId, questionCommentDto);

        CommentRespDto<?> response = CommentRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .comment(commentMapper.getQuestionCommentInfos(questionCommentService.findAllByQuestion(id)))
                .build();

        //Todo : ?

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/comments/{commentId}") //게시글 댓글 삭제 요청
    @Transactional
    public ResponseEntity<?> deleteQuestionComment(@PathVariable Long id, @PathVariable Long commentId) {

        //Todo : 댓글 게시자만 삭제할 수 있습니다.

        //Todo : 해당 질문의 댓글을 삭제합니다.
        questionCommentService.deleteById(id, commentId);

        CommentRespDto<?> response = CommentRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .comment(questionCommentService.findAllByQuestion(id))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
