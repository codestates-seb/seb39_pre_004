package team.pre004.stackoverflowclone.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.dto.common.CMRespDto;
import team.pre004.stackoverflowclone.dto.post.request.AnswerDto;
import team.pre004.stackoverflowclone.dto.post.response.AnswerInfoDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.ResponseCode;
import team.pre004.stackoverflowclone.handler.exception.CustomNotAccessItemsException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.mapper.AnswerMapper;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.service.AnswerService;
import team.pre004.stackoverflowclone.service.CommonService;
import team.pre004.stackoverflowclone.web.config.auth.PrincipalDetails;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final CommentMapper commentMapper;
    private final CommonService commonService;
    private final UsersRepository usersRepository;

    Users users = Users.builder()
            .password("4321")
            .name("답글ward")
            .email("ward@ward.net")
            .bio("답글쓰는사람입니다.")
            .build();

    @PostMapping("/{questionId}/add") // 답글 작성 요청
    public ResponseEntity<?> addAnswer(@PathVariable Long questionId, @RequestBody AnswerDto answerDto) {

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        Answer answer = answerService.save(
                answerMapper.answerDtoToAnswer(
                        principalDetails.getUsers(), questionId, answerDto
                )
        );

        AnswerInfoDto answerInfoDto = answerMapper.getAnswerInfo(answer);

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(answerInfoDto)
                .message("답글을 추가하였습니다.")
                .build();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/{answerId}/edit") // 답글 수정 요청
    public ResponseEntity<?> editAnswer(@PathVariable Long answerId, @RequestBody AnswerDto answerDto) {

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        if (answerService.findById(answerId).isEmpty()) //해당 게시물이 없을 때 에러메세지
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID);

        if (principalDetails.getUsers().getOwnerId() != answerId) //접근 유저가 아닐경우
            throw new CustomNotAccessItemsException(ExceptionMessage.NOT_ACCESS_EDIT_ANSWER);

        Answer answer = answerService.update(answerId, answerMapper.answerDtoToAnswer(
                principalDetails.getUsers(), answerId, answerDto
        ));

        AnswerInfoDto answerInfoDto = answerMapper.getAnswerInfo(answer);

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(answerInfoDto)
                .message("답글을 변경하였습니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity<?> selectAnswer(@PathVariable Long id) {

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        boolean isAccepted = answerService.acceptAnswer(principalDetails.getUsers().getOwnerId(), id);

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(isAccepted)
                .message("답글을 채택하였습니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/selected/undo") //답글 채택 취소 요청
    public ResponseEntity<?> undoSelectAnswer(@PathVariable Long id) {

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        boolean isAccepted = answerService.acceptAnswerUndo(principalDetails.getUsers().getOwnerId(), id);

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .data(isAccepted)
                .message("답글 채택을 취소하였습니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }







}
