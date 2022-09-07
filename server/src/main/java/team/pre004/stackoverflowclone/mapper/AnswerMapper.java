package team.pre004.stackoverflowclone.mapper;

import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.response.AnswerInfoDto;
import team.pre004.stackoverflowclone.dto.post.request.AnswerPostDto;

import java.util.Set;


public interface AnswerMapper {
    Set<AnswerInfoDto> getAnswerInfos(Set<Answer> answers);
    Answer answerPostDtoToAnswer(Users owner, Long questionId, AnswerPostDto answerDto);
    AnswerInfoDto getAnswerInfo(Answer answer);
}
