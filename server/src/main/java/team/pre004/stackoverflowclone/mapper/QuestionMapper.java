package team.pre004.stackoverflowclone.mapper;

import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.QuestionDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionInfoDto;

import java.util.List;


public interface QuestionMapper {

    List<QuestionInfoDto> getQuestionInfos(List<Question> questions);
    Question questionDtoToQuestion(Users owner, QuestionDto questionDto);
    QuestionInfoDto getQuestionInfo(Question question);

}
