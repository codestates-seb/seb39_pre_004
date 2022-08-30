package team.pre004.stackoverflowclone.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.post.repository.QuestionRepository;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionCommentInfoDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointItemsExeption;
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

    @Override
    public Set<QuestionCommentInfoDto> getQuestionCommentInfo(Set<QuestionComment> questionComment) {
        if (questionComment == null)
            return Collections.emptySet();


        Set<QuestionCommentInfoDto> questionCommentInfos = new LinkedHashSet<>(questionComment.size());
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
                () -> new CustomNullPointItemsExeption(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
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
                .questionCommentId(questionComment.getId())
                .body(questionComment.getBody())
                .createDate(questionComment.getCreateDate())
                .modDate(questionComment.getModDate())
                .build();
    }
}
