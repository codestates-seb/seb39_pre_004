package team.pre004.stackoverflowclone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.pre004.stackoverflowclone.dto.request.QuestionPostDto;
import team.pre004.stackoverflowclone.dto.response.QuestionDto;
import team.pre004.stackoverflowclone.dto.response.QuestionInfoDto;

import java.util.Optional;

public interface QuestionService {

    Page<QuestionInfoDto> getAllQuestions(Pageable pageable);
    Optional<QuestionDto> getQuestion(Long questionId);
    QuestionDto createQuestion(QuestionPostDto questionPostDto);
    QuestionDto editQuestion(QuestionPostDto questionPostDto);
    void deleteQuestion(Long questionId);
}
