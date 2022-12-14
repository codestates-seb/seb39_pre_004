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


    @PostMapping("/{questionId}/add") // ?????? ?????? ??????
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
                .message("????????? ?????????????????????.")
                .build();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/{answerId}/edit") // ?????? ?????? ??????
    public ResponseEntity<?> editAnswer(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @PathVariable Long answerId,
                                        @RequestBody AnswerPostDto answerPostDto) {

        Users user = principalDetails.getOwner();
        if (answerService.findById(answerId).isEmpty()) //?????? ???????????? ?????? ??? ???????????????
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID);

        authService.isAuthenticatedUser(user.getOwnerId(), answerService.findById(answerId).get().getOwner().getOwnerId());

        Answer answer = answerService.update(answerId, answerMapper.answerPostDtoToAnswer(
                principalDetails.getOwner(), answerId, answerPostDto
        ));

        AnswerInfoDto answerInfoDto = answerMapper.getAnswerInfo(answer);
        AnswerRespDto<?> response = AnswerRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .answer(answerInfoDto)
                .message("????????? ?????????????????????.")
                .build();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{answerId}") // ?????? ?????? ??????
    public ResponseEntity<?> deleteAnswer(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                          @PathVariable Long answerId) {

        Users user = principalDetails.getOwner();
        if (answerService.findById(answerId).isEmpty()) //?????? ???????????? ?????? ??? ???????????????
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID);

        Question question = answerService.findById(answerId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID)
        ).getQuestion();

        authService.isAuthenticatedUser(user.getOwnerId(), answerService.findById(answerId).get().getOwner().getOwnerId());

        answerService.deleteById(answerId);
        Set<Answer> answers = answerService.findAllByQuestion(question);

        AnswerRespDto<?> response = AnswerRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("????????? ??????????????? ?????????????????????.")
                .answer(answers)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{answerId}/likes-up") // ?????? ????????? ??????
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
                .message("?????? ????????? ???????????????.")
                .like(likes)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{answerId}/likes-down") // ?????? ????????? ??????
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
                .message("?????? ????????? ???????????????.")
                .like(likes)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/{answerId}/likes-up/undo") // ?????? ????????? ????????????
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
                .message("?????? ????????? ?????? ???????????????.")
                .like(likes)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{answerId}/likes-down/undo") // ?????? ????????? ?????? ??????
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
                .message("?????? ????????? ?????? ???????????????.")
                .like(likes)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{answerId}/comments") //?????? ?????? ?????? ??????
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

    @PutMapping("/{answerId}/comments/{commentId}") //?????? ?????? ?????? ??????
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

    @DeleteMapping("/{answerId}/comments/{commentId}") //?????? ?????? ?????? ??????
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

    @PostMapping("/{id}/selected") // ?????? ?????? ??????
    public ResponseEntity<?> selectAnswer(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id) {


        boolean isAccepted = answerService.acceptAnswer(principalDetails.getOwner().getOwnerId(), id);

        AnswerRespDto<?> response = AnswerRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .answer(answerMapper.getAnswerInfo(answerService.findById(id).orElseThrow(
                        () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID))
                ))
                .message("????????? ?????????????????????.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/selected/undo") //?????? ?????? ?????? ??????
    public ResponseEntity<?> undoSelectAnswer(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id) {


        boolean isAccepted = answerService.acceptAnswerUndo(principalDetails.getOwner().getOwnerId(), id);

        AnswerRespDto<?> response = AnswerRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .answer(answerMapper.getAnswerInfo(answerService.findById(id).orElseThrow(
                        () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID))
                ))
                .message("?????? ????????? ?????????????????????.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
