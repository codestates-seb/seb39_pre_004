package team.pre004.stackoverflowclone.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.dto.post.request.AnswerDto;
import team.pre004.stackoverflowclone.dto.post.response.AnswerInfoDto;
import team.pre004.stackoverflowclone.mapper.AnswerMapper;
import team.pre004.stackoverflowclone.mapper.UsersMapper;


import java.util.*;

@Component
@RequiredArgsConstructor
public class AnswerMapperImpl implements AnswerMapper {

    private final UsersMapper usersMapper;

    @Override
    public List<AnswerInfoDto> getAnswerInfos(List<Answer> answers) {

        if(answers == null)
            return Collections.emptyList();

        List<AnswerInfoDto> answerInfos = new LinkedList<>();
        for(Answer answer :answers )
        {
            answerInfos.add(getAnswerInfo(answer));
        }
        return answerInfos;
    }

    @Override
    public Answer answerDtoToAnswer(AnswerDto answerDto) {
        return null;
    }

    public AnswerInfoDto getAnswerInfo(Answer answer) {
        if (answer == null)
            return null;

        return AnswerInfoDto.builder()
                .owner(usersMapper.getUserInfo(answer.getOwner()))
                .likes(answer.getLikes())
                .body(answer.getBody())
                .likes(answer.getLikes())
                .createDate(answer.getCreateDate())
                .modDate(answer.getModDate())
                .build();
    }


}
