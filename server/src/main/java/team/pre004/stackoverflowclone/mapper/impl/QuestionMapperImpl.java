package team.pre004.stackoverflowclone.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.QuestionPostDto;
import team.pre004.stackoverflowclone.dto.post.response.*;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointUsersException;
import team.pre004.stackoverflowclone.mapper.AnswerMapper;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.mapper.QuestionMapper;
import team.pre004.stackoverflowclone.mapper.UsersMapper;

import java.util.*;

@Component
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper {

    private final UsersMapper usersMapper;
    private final CommentMapper commentMapper;
    private final AnswerMapper answerMapper;


    @Override
    public Set<QuestionInfoDto> getQuestionInfos(Set<Question> questions) {
        if (questions == null)
            return new LinkedHashSet<>();

        Set<QuestionInfoDto> questionInfos = new LinkedHashSet<>();
        for (Question questionInfo : questions) {
            questionInfos.add(getQuestionInfo(questionInfo));
        }

        return questionInfos;
    }

    @Override
    public Question questionPostDtoToQuestion(Users owner, QuestionPostDto questionPostDto) {
        if (questionPostDto == null)
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID);

        if (owner == null)
            throw new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID);

        return Question.builder()
                .owner(owner)
                .title(questionPostDto.getTitle())
                .body(questionPostDto.getBody())
                .tags(questionPostDto.getTags())
                .build();
    }

    @Override
    public Set<QuestionIndexDto> getQuestionIndexs(Set<Question> questions) {
        if (questions == null)
            return new LinkedHashSet<>();

        Set<QuestionIndexDto> questionIndexs = new LinkedHashSet<>();
        for (Question questionIndex : questions) {
            questionIndexs.add(getQuestionIndex(questionIndex));
        }

        return questionIndexs;
    }

    @Override
    public QuestionIndexDto getQuestionIndex(Question question) {

        if (question == null)
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID);

        UserInfoDto userInfo = usersMapper.getUserInfo(question.getOwner());
        Set<Answer> answers = question.getAnswers();

        return QuestionIndexDto.builder()
                .owner(userInfo)
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .body(question.getBody())
                .link(question.getLink())
                .isAccepted(question.isAccepted())
                .views(question.getView())
                .likes(question.getLikes())
                .tags(question.getTags())
                .createDate(question.getCreateDate())
                .modDate(question.getModDate())
                .answers(answerMapper.getAnswerInfos(answers).size())
                .build();

    }

    @Override
    public QuestionInfoDto getQuestionInfo(Question question) {
        if (question == null)
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID);


        UserInfoDto userInfo = usersMapper.getUserInfo(question.getOwner());
        Set<Answer> answers = question.getAnswers();
        Set<QuestionComment> comments = question.getQuestionComment();

        return QuestionInfoDto.builder()
                .owner(userInfo)
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .body(question.getBody())
                .link(question.getLink())
                .isAccepted(question.isAccepted())
                .views(question.getView())
                .likes(question.getLikes())
                .createDate(question.getCreateDate())
                .modDate(question.getModDate())
                .answers(answerMapper.getAnswerInfos(answers))
                .comments(commentMapper.getQuestionCommentInfos(comments))
                .build();

    }

    @Override
    public QuestionPostDto getQuestionPostDto(Question question) {

        if (question == null)
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID);

        UserInfoDto userInfo = usersMapper.getUserInfo(question.getOwner());

        return QuestionPostDto.builder()
                .owner(userInfo)
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .body(question.getBody())
                .tags(question.getTags())
                .link(question.getLink())
                .build();
    }
}
