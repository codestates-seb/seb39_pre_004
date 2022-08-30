package team.pre004.stackoverflowclone.mapper;

import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.AnswerDto;
import team.pre004.stackoverflowclone.dto.post.response.AnswerInfoDto;

import java.util.List;
import java.util.Set;


public interface AnswerMapper {
    Set<AnswerInfoDto> getAnswerInfos(Set<Answer> answers);
    Answer answerDtoToAnswer(Users owner, AnswerDto answerDto);
    AnswerInfoDto getAnswerInfo(Answer answer);
}
