package team.pre004.stackoverflowclone.mapper;

import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.response.QuestionIndexDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionInfoDto;
import team.pre004.stackoverflowclone.dto.post.request.QuestionPostDto;

import java.util.Set;


public interface QuestionMapper {

    Set<QuestionInfoDto> getQuestionInfos(Set<Question> questions);
    Question questionPostDtoToQuestion(Users owner, QuestionPostDto questionPostDto);
    Set<QuestionIndexDto> getQuestionIndexs(Set<Question> questions);
    QuestionIndexDto getQuestionIndex(Question question);
    QuestionInfoDto getQuestionInfo(Question question);
    QuestionPostDto getQuestionPostDto(Question question);

}
