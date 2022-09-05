package team.pre004.stackoverflowclone.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.dto.common.*;
import team.pre004.stackoverflowclone.dto.post.PostType;
import team.pre004.stackoverflowclone.dto.post.request.AnswerCommentDto;
import team.pre004.stackoverflowclone.dto.post.response.AnswerInfoDto;
import team.pre004.stackoverflowclone.dto.post.request.AnswerPostDto;
import team.pre004.stackoverflowclone.dto.post.response.LikesDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.ResponseCode;
import team.pre004.stackoverflowclone.handler.exception.CustomNotAccessItemsException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentByIdException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.mapper.AnswerMapper;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.security.PrincipalDetails;
import team.pre004.stackoverflowclone.service.AnswerCommentService;
import team.pre004.stackoverflowclone.service.AnswerService;
import team.pre004.stackoverflowclone.service.AuthService;
import team.pre004.stackoverflowclone.service.CommonService;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final CommentMapper commentMapper;
    private final CommonService commonService;
    private final AnswerCommentService answerCommentService;
    private final UsersRepository usersRepository;
    private final AuthService authService;


    @PostMapping("/{questionId}/add") // 답글 작성 요청
    public ResponseEntity<?> addAnswer(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                       @PathVariable Long questionId,
                                       @RequestBody AnswerPostDto answerPostDto) {

        Answer answer = answerService.save(
                answerMapper.answerPostDtoToAnswer(
                        principalDetails.getOwner(), questionId, answerPostDto
                )
        );

        AnswerInfoDto answerInfoDto = answerMapper.getAnswerInfo(answer);

        AnswerRespDto<?> response = AnswerRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .answer(answerInfoDto)
                .message("답글을 추가하였습니다.")
                .build();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/{answerId}/edit") // 답글 수정 요청
    public ResponseEntity<?> editAnswer(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @PathVariable Long answerId,
                                        @RequestBody AnswerPostDto answerPostDto) {

        Users user = principalDetails.getOwner();
        if (answerService.findById(answerId).isEmpty()) //해당 게시물이 없을 때 에러메세지
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID);

        authService.isAuthenticatedUser(user.getOwnerId(), answerService.findById(answerId).get().getOwner().getOwnerId());

        Answer answer = answerService.update(answerId, answerMapper.answerPostDtoToAnswer(
                principalDetails.getOwner(), answerId, answerPostDto
        ));

        AnswerInfoDto answerInfoDto = answerMapper.getAnswerInfo(answer);
        AnswerRespDto<?> response = AnswerRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .answer(answerInfoDto)
                .message("답글을 변경하였습니다.")
                .build();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{answerId}") // 답글 삭제 요청
    public ResponseEntity<?> deleteAnswer(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                          @PathVariable Long answerId) {

        Users user = principalDetails.getOwner();
        if (answerService.findById(answerId).isEmpty()) //해당 게시물이 없을 때 에러메세지
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID);

        Question question = answerService.findById(answerId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID)
        ).getQuestion();

        authService.isAuthenticatedUser(user.getOwnerId(), answerService.findById(answerId).get().getOwner().getOwnerId());

        answerService.deleteById(answerId);
        Set<Answer> answers = answerService.findAllByQuestion(question);

        AnswerRespDto<?> response = AnswerRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("답글이 정상적으로 삭제되었습니다.")
                .answer(answers)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{answerId}/likes-up") // 답글 좋아요 요청
    public ResponseEntity<?> upAnswerLike(@PathVariable Long answerId,
                                          @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getOwner().getOwnerId();

        LikesDto likes = LikesDto.builder()
                .likes(answerService.selectLikeUp(userId, answerId))
                .postId(answerId)
                .postType(PostType.ANSWER)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("답글 좋아요 요청입니다.")
                .like(likes)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{answerId}/likes-down") // 답글 싫어요 요청
    public ResponseEntity<?> downAnswerLike(@PathVariable Long answerId,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getOwner().getOwnerId();

        LikesDto likes = LikesDto.builder()
                .likes(answerService.selectLikeDown(userId, answerId))
                .postId(answerId)
                .postType(PostType.ANSWER)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("답글 싫어요 요청입니다.")
                .like(likes)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/{answerId}/likes-up/undo") // 답글 좋아요 요청취소
    public ResponseEntity<?> upUndoAnswerLike(@PathVariable Long answerId,
                                              @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getOwner().getOwnerId();

        LikesDto likes = LikesDto.builder()
                .likes(answerService.selectLikeUpUndo(userId, answerId))
                .postId(answerId)
                .postType(PostType.ANSWER)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("답글 좋아요 취소 요청입니다.")
                .like(likes)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{answerId}/likes-down/undo") // 답글 싫어요 요청 취소
    public ResponseEntity<?> downUndoAnswerLike(@PathVariable Long answerId,
                                                @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getOwner().getOwnerId();

        LikesDto likes = LikesDto.builder()
                .likes(answerService.selectLikeDownUndo(userId, answerId))
                .postId(answerId)
                .postType(PostType.ANSWER)
                .build();

        LikeRespDto<?> response = LikeRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("답글 싫어요 요청 취소입니다.")
                .like(likes)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{answerId}/comments") //답글 댓글 작성 요청
    public ResponseEntity<?> addAnswerComment(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                              @PathVariable Long answerId,
                                              @RequestBody AnswerCommentDto answerCommentDto) {

        answerCommentService.save(
                commentMapper.getAnswerComment(principalDetails.getOwner(), answerId, answerCommentDto));

        CommentRespDto<?> response = CommentRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .comment(commentMapper.getAnswerCommentInfos(answerCommentService.findAllByAnswer(answerId)))
                .build();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{answerId}/comments/{commentId}") //답글 댓글 수정 요청
    public ResponseEntity updateAnswerComment(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long answerId, @PathVariable Long commentId,
            @RequestBody AnswerCommentDto answerCommentDto) {

        Users users = principalDetails.getOwner();

        authService.isAuthenticatedUser(users.getOwnerId(), answerCommentService.findById(commentId).getOwner().getOwnerId());
        answerCommentService.update(answerId, commentId, answerCommentDto);


        CommentRespDto<?> response = CommentRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .comment(commentMapper.getAnswerCommentInfos(answerCommentService.findAllByAnswer(answerId)))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{answerId}/comments/{commentId}") //답글 댓글 삭제 요청
    public ResponseEntity<?> deleteAnswerComment(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long answerId, @PathVariable Long commentId) {


        Users users = principalDetails.getOwner();

        authService.isAuthenticatedUser(users.getOwnerId(), answerCommentService.findById(commentId).getOwner().getOwnerId());

        answerCommentService.deleteById(answerId, commentId);

        CommentRespDto<?> response = CommentRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .comment(answerCommentService.findAllByAnswer(answerId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/selected") // 답글 채택 요청
    public ResponseEntity<?> selectAnswer(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id) {


        boolean isAccepted = answerService.acceptAnswer(principalDetails.getOwner().getOwnerId(), id);

        AnswerRespDto<?> response = AnswerRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .answer(answerMapper.getAnswerInfo(answerService.findById(id).orElseThrow(
                        () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID))
                ))
                .message("답글을 채택하였습니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/selected/undo") //답글 채택 취소 요청
    public ResponseEntity<?> undoSelectAnswer(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id) {


        boolean isAccepted = answerService.acceptAnswerUndo(principalDetails.getOwner().getOwnerId(), id);

        AnswerRespDto<?> response = AnswerRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .answer(answerMapper.getAnswerInfo(answerService.findById(id).orElseThrow(
                        () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID))
                ))
                .message("답글 채택을 취소하였습니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
