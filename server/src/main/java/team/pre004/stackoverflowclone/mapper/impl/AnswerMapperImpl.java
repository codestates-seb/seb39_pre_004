package team.pre004.stackoverflowclone.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.AnswerComment;

import team.pre004.stackoverflowclone.domain.post.repository.QuestionRepository;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import team.pre004.stackoverflowclone.dto.post.response.AnswerInfoDto;
import team.pre004.stackoverflowclone.dto.post.request.AnswerPostDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;

import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointUsersException;
import team.pre004.stackoverflowclone.mapper.AnswerMapper;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.mapper.UsersMapper;


import java.util.*;

@Component
@RequiredArgsConstructor
public class AnswerMapperImpl implements AnswerMapper {

    private final UsersMapper usersMapper;
    private final CommentMapper commentMapper;
    private final QuestionRepository questionRepository;

    @Override
    public Set<AnswerInfoDto> getAnswerInfos(Set<Answer> answers) {

        if(answers == null)
            return new LinkedHashSet<>();

        Set<AnswerInfoDto> answerInfos = new LinkedHashSet<>();
        for(Answer answer :answers )
        {
            answerInfos.add(getAnswerInfo(answer));
        }
        return answerInfos;
    }

    @Override
    public Answer answerPostDtoToAnswer(Users owner, Long questionId, AnswerPostDto answerPostDto) {

        if(answerPostDto == null)
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID);

        if(owner == null)
            throw new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID);

        questionRepository.findById(questionId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );



        return Answer.builder()
                .owner(owner)
                .question(questionRepository.findById(questionId).orElseThrow())
                .body(answerPostDto.getBody())
                .build();

    }


    public AnswerInfoDto getAnswerInfo(Answer answer) {
        if (answer == null)
            return null;

        Set<AnswerComment> comments = answer.getAnswerComments();

        return AnswerInfoDto.builder()
                .owner(usersMapper.getUserInfo(answer.getOwner()))
                .answerId(answer.getAnswerId())
                .likes(answer.getLikes())
                .body(answer.getBody())
                .likes(answer.getLikes())
                .isAccepted(answer.isAccepted())
                .createDate(answer.getCreateDate())
                .modDate(answer.getModDate())
                .comments(commentMapper.getAnswerCommentInfos(comments))
                .build();
    }


}
