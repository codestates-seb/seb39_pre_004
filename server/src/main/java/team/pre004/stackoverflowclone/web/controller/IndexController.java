package team.pre004.stackoverflowclone.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.pre004.stackoverflowclone.dto.common.CMRespDto;
import team.pre004.stackoverflowclone.dto.common.QuestionRespDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionIndexDto;
import team.pre004.stackoverflowclone.handler.ResponseCode;
import team.pre004.stackoverflowclone.mapper.QuestionMapper;
import team.pre004.stackoverflowclone.security.PrincipalDetails;
import team.pre004.stackoverflowclone.service.QuestionService;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class IndexController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping("/") //메인 페이지 (게시글들 전체 조회)
    public ResponseEntity<?> index() {

        return new ResponseEntity<>("redirect:/", HttpStatus.OK);
    }

    @GetMapping("/login_status")
    public ResponseEntity<?> getLoginSession(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        QuestionRespDto<?> response = QuestionRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .question(principalDetails.getOwner())
                .message("인증되었습니다.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/main") //메인 페이지 (게시글들 전체 조회)
    public ResponseEntity<?> main() {

        Set<QuestionIndexDto> questionIndexs = questionMapper.getQuestionIndexs(questionService.findAll());

        QuestionRespDto<?> response = QuestionRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("메인 페이지 입니다.")
                .question(questionIndexs)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
