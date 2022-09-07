package team.pre004.stackoverflowclone.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import team.pre004.stackoverflowclone.handler.exception.CustomNotAccessItemsException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.mapper.QuestionMapper;
import team.pre004.stackoverflowclone.web.config.auth.PrincipalDetails;
import team.pre004.stackoverflowclone.service.CommonService;
import team.pre004.stackoverflowclone.service.QuestionCommentService;
import team.pre004.stackoverflowclone.service.QuestionService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/questions")
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

    @GetMapping("/add") // 게시글 작성 페이지
    public ResponseEntity<?> getAddQuestionForm() {

        CMRespDto<?> response = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("게시글 조회 페이지입니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add") //게시글 작성 요청
    public ResponseEntity<?> addQuestion(@RequestBody QuestionPostDto questionPostDto) {

        //Todo : 로그인한 유저만 작성 요청을 할 수 있습니다.

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        //Todo : 작성한 title, body, tags를 DB에 저장합니다.

        Question question = questionService.save(
                questionMapper.questionPostDtoToQuestion(
                        principalDetails.getUsers(), questionPostDto)
        );
        //Todo : 작성한 질문 아이디의 조회 페이지로 리다이렉션을 합니다.
        return new ResponseEntity<>(question.getQuestionId(), HttpStatus.OK);
    }

    @GetMapping("/{id}") // 게시글 조회 페이지
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
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody QuestionPostDto questionPostDto) {

        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        if (questionService.findById(id).isEmpty()) //해당 게시물이 없을 때 에러메세지
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID);

        System.out.println("id = " + id + ", questionPostDto = " + questionPostDto);

        Question question = questionService.update(id, questionMapper.questionPostDtoToQuestion(
                principalDetails.getUsers(), questionPostDto));

        System.out.println("id = " + id + ", question.body = " + question.getBody());

        return new ResponseEntity<>(getQuestion(question.getQuestionId()), HttpStatus.MOVED_PERMANENTLY);
    }

    @DeleteMapping("/{id}") // 게시글 삭제 요청
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {

        questionService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/likes-up") // 게시글 좋아요 요청
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
    public ResponseEntity<?> addQuestionComment(@PathVariable Long id, @RequestBody QuestionCommentDto questionCommentDto) {


        //Todo : 로그인한 회원만 댓글을 작성 할 수 있습니다.

        //Todo : 작성한 댓글을 DB에 저장합니다.
        PrincipalDetails principalDetails = PrincipalDetails.builder().
                users(usersRepository.save(users))
                .build();

        questionCommentService.save(
                commentMapper.getQuestionComment(principalDetails.getUsers(), id, questionCommentDto));

        CommentRespDto<?> response = CommentRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .comment(commentMapper.getQuestionCommentInfos(questionCommentService.findAllByQuestion(id)))
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

        CommentRespDto<?> response = CommentRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .comment(commentMapper.getQuestionCommentInfos(questionCommentService.findAllByQuestion(id)))
                .build();

        //Todo : ?

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/comments/{commentId}") //게시글 댓글 삭제 요청
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
