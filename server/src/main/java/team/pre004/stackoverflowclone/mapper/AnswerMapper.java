package team.pre004.stackoverflowclone.mapper;

import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.dto.post.response.AnswerInfoDto;

import java.util.Set;


public interface AnswerMapper {
    Set<AnswerInfoDto> answerToAnswerInfoDto(Set<Answer> answers);
}
