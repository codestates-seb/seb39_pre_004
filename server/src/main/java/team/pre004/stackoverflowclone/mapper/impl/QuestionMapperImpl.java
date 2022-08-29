package team.pre004.stackoverflowclone.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.QuestionDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionInfoDto;
import team.pre004.stackoverflowclone.dto.post.response.UserInfoDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointItemsExeption;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointUsersException;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.mapper.QuestionMapper;
import team.pre004.stackoverflowclone.mapper.UsersMapper;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper {

    private final UsersMapper usersMapper;
    private final CommentMapper commentMapper;
    @Override
    public Question questionDtoToQuestion(Users users, QuestionDto questionDto) {

        if(questionDto == null){
            throw new CustomNullPointItemsExeption(ExceptionMessage.NOT_CONTENT_QUESTION_ID);
        }

        if(users == null){
            throw new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID);
        }

        return Question.builder()
                .owner(users)
                .title(questionDto.getTitle())
                .body(questionDto.getBody())
                .tags(questionDto.getTags())
                .build();
    }

    @Override
    public QuestionInfoDto getQuestionInfo(Question question) {
        if(question == null){
            throw new CustomNullPointItemsExeption(ExceptionMessage.NOT_CONTENT_QUESTION_ID);
        }

        UserInfoDto userInfo = usersMapper.getUserInfo(question.getOwner());
        Set<Answer> answers = question.getAnswers();
        Set<QuestionComment> comments =question.getQuestionComment();

        return QuestionInfoDto.builder()
                .owner(userInfo)
                .questionId(question.getId())
                .title(question.getTitle())
                .body(question.getBody())
                .link(question.getLink())
                .views(question.getView())
                .likes(question.getLikes())
                .createDate(question.getCreateDate())
                .modDate(question.getModDate())
                .comments(commentMapper.getQuestionCommentInfo(question.getQuestionComment()))
                .build();

    }
}
