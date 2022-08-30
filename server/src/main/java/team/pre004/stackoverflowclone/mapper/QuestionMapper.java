package team.pre004.stackoverflowclone.mapper;

import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.QuestionDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionInfoDto;


public interface QuestionMapper {
    Question questionDtoToQuestion(Users users, QuestionDto questionDto);

    QuestionInfoDto getQuestionInfo(Question question);

}
