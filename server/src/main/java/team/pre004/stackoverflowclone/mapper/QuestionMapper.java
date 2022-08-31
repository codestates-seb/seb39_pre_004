package team.pre004.stackoverflowclone.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.QuestionDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionIndexDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionInfoDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionPostDto;

import java.util.List;
import java.util.Set;


public interface QuestionMapper {

    Set<QuestionInfoDto> getQuestionInfos(Set<Question> questions);
    Question questionDtoToQuestion(Users owner, QuestionDto questionDto);

    Set<QuestionIndexDto> getQuestionIndexs(Set<Question> questions);
    QuestionIndexDto getQuestionIndex(Question question);

    QuestionInfoDto getQuestionInfo(Question question);

    QuestionPostDto getQuestionPostDto(Question question);

}
