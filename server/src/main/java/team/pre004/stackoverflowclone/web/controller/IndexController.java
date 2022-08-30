package team.pre004.stackoverflowclone.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.pre004.stackoverflowclone.dto.common.CMRespDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionInfoDto;
import team.pre004.stackoverflowclone.handler.ResponseCode;
import team.pre004.stackoverflowclone.mapper.QuestionMapper;
import team.pre004.stackoverflowclone.service.QuestionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class IndexController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping("/") //메인 페이지 (게시글들 전체 조회)
    public ResponseEntity<?> index() {

        List<QuestionInfoDto> questionInfoDto = questionMapper.getQuestionInfos(questionService.findAll());

        CMRespDto<?> cmRespDto = CMRespDto.builder()
                .code(ResponseCode.SUCCESS)
                .message("메인 페이지 입니다.")
                .data(questionInfoDto)
                .build();

        return new ResponseEntity<>(cmRespDto, HttpStatus.OK);
    }

}
