package team.pre004.stackoverflowclone.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.AnswerComment;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.post.repository.AnswerRepository;
import team.pre004.stackoverflowclone.domain.post.repository.QuestionRepository;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.AnswerCommentDto;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;
import team.pre004.stackoverflowclone.dto.post.response.AnswerCommentInfoDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionCommentInfoDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.mapper.UsersMapper;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommentMapperImpl implements CommentMapper {

    private final UsersMapper usersMapper;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public Set<QuestionCommentInfoDto> getQuestionCommentInfos(Set<QuestionComment> questionComment) {
        if (questionComment == null)
            return Collections.emptySet();


        Set<QuestionCommentInfoDto> questionCommentInfos = new LinkedHashSet<>();
        for (QuestionComment comment : questionComment) {
            questionCommentInfos.add(getQuestionCommentInfo(comment));
        }
        return questionCommentInfos;
    }

    @Override
    public QuestionComment getQuestionComment(Users owner, Long questionId, QuestionCommentDto questionCommentDto) {
        if (questionCommentDto == null)
            return null;

        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );


        return QuestionComment.builder()
                .owner(owner)
                .question(question)
                .body(questionCommentDto.getBody())
                .build();
    }

    public QuestionCommentInfoDto getQuestionCommentInfo(QuestionComment questionComment) {
        if (questionComment == null)
            return null;

        return QuestionCommentInfoDto.builder()
                .owner(usersMapper.getUserInfo(questionComment.getOwner()))
                .questionCommentId(questionComment.getQuestionCommentId())
                .body(questionComment.getBody())
                .createDate(questionComment.getCreateDate())
                .modDate(questionComment.getModDate())
                .build();
    }

    @Override
    public Set<AnswerCommentInfoDto> getAnswerCommentInfos(Set<AnswerComment> answerComments) {
        if (answerComments == null)
            return Collections.emptySet();

        Set<AnswerCommentInfoDto> answerCommentInfos = new LinkedHashSet<>();
        for (AnswerComment comment : answerComments) {
            answerCommentInfos.add(getAnswerCommentInfo(comment));
        }
        return answerCommentInfos;
    }

    @Override
    public AnswerComment getAnswerComment(Users owner, Long answerId, AnswerCommentDto answerCommentDto) {
        if (answerCommentDto == null)
            return null;

        Answer answer = answerRepository.findById(answerId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID)
        );

        return AnswerComment.builder()
                .owner(owner)
                .answer(answer)
                .body(answerCommentDto.getBody())
                .build();
    }

    @Override
    public AnswerCommentInfoDto getAnswerCommentInfo(AnswerComment answerComment) {
        if (answerComment == null)
            return null;

        return AnswerCommentInfoDto.builder()
                .owner(usersMapper.getUserInfo(answerComment.getOwner()))
                .answerCommentId(answerComment.getAnswerCommentId())
                .body(answerComment.getBody())
                .createDate(answerComment.getCreateDate())
                .modDate(answerComment.getModDate())
                .build();
    }
}
