package team.pre004.stackoverflowclone.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.dto.post.response.AnswerInfoDto;
import team.pre004.stackoverflowclone.mapper.AnswerMapper;
import team.pre004.stackoverflowclone.mapper.UsersMapper;


import java.util.LinkedHashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AnswerMapperImpl implements AnswerMapper {

    private final UsersMapper usersMapper;

    @Override
    public Set<AnswerInfoDto> answerToAnswerInfoDto(Set<Answer> answers) {
        Set<AnswerInfoDto> answerInfos = new LinkedHashSet<>(answers.size());
        for(Answer answer :answers )
        {
            answerInfos.add(mappingAnswerDto(answer));
        }
        return answerInfos;
    }

    protected AnswerInfoDto mappingAnswerDto(Answer answer) {
        if (answer == null)
            return null;

        return AnswerInfoDto.builder()
                .owner(usersMapper.getUserInfo(answer.getOwner()))
                .likes(answer.getLikes())
                .body(answer.getBody())
                .createDate(answer.getCreateDate())
                .modDate(answer.getModDate())
                .build();
    }


}
