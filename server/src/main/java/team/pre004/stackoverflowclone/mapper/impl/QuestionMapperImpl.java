package team.pre004.stackoverflowclone.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.QuestionDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionCommentInfoDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionInfoDto;
import team.pre004.stackoverflowclone.dto.post.response.UserInfoDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointUsersException;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.mapper.QuestionMapper;
import team.pre004.stackoverflowclone.mapper.UsersMapper;

import java.util.*;

@Component
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper {

    private final UsersMapper usersMapper;
    private final CommentMapper commentMapper;


    @Override
    public List<QuestionInfoDto> getQuestionInfos(List<Question> questions) {
        if (questions == null)
            return Collections.emptyList();

        List<QuestionInfoDto> questionInfos = new LinkedList<>();
        for(Question questionInfo : questions) {
            questionInfos.add(getQuestionInfo(questionInfo));
        }

        return questionInfos;
    }


    @Override
    public Question questionDtoToQuestion(Users owner, QuestionDto questionDto) {

        if(questionDto == null)
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID);


        if(owner == null)
            throw new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID);


        return Question.builder()
                .owner(owner)
                .title(questionDto.getTitle())
                .body(questionDto.getBody())
                .tags(questionDto.getTags())
                .build();
    }

    @Override
    public QuestionInfoDto getQuestionInfo(Question question) {
        if(question == null)
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID);


        UserInfoDto userInfo = usersMapper.getUserInfo(question.getOwner());
        Set<Answer> answers = question.getAnswers();
        Set<QuestionComment> comments =question.getQuestionComment();

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
                .comments(commentMapper.getQuestionCommentInfo(question.getQuestionComment()))
                .build();

    }
}
