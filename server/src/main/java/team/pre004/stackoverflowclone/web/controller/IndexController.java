package team.pre004.stackoverflowclone.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.pre004.stackoverflowclone.dto.common.CMRequestDto;
import team.pre004.stackoverflowclone.dto.common.CMRespDto;
import team.pre004.stackoverflowclone.dto.common.QuestionRespDto;
import team.pre004.stackoverflowclone.dto.post.request.QuestionPostDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionIndexDto;
import team.pre004.stackoverflowclone.handler.ResponseCode;
import team.pre004.stackoverflowclone.mapper.QuestionMapper;
import team.pre004.stackoverflowclone.service.QuestionService;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class IndexController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping("/")
    public ResponseEntity<?> index(@RequestBody QuestionPostDto questionPostDto) {

        return new ResponseEntity<>("redirect:/", HttpStatus.OK);
    }

    @PostMapping("/test1")
    public ResponseEntity<?> test1(@RequestBody QuestionPostDto questionPostDto) {
        log.info(questionPostDto.toString());
        return new ResponseEntity<>(questionPostDto, HttpStatus.OK);
    }

    @PostMapping("/test2")
    public ResponseEntity<?> test2(@RequestBody CMRequestDto<?> cmRequestDto) {
        log.info(cmRequestDto.toString());
        return new ResponseEntity<>(cmRequestDto, HttpStatus.OK);
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
