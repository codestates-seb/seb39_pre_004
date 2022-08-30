package team.pre004.stackoverflowclone.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;
import team.pre004.stackoverflowclone.dto.post.response.QuestionCommentInfoDto;
import team.pre004.stackoverflowclone.mapper.CommentMapper;
import team.pre004.stackoverflowclone.mapper.UsersMapper;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommentMapperImpl implements CommentMapper {

    private final UsersMapper usersMapper;

    @Override
    public Set<QuestionCommentInfoDto> getQuestionCommentInfo(Set<QuestionComment> questionComment) {
        if (questionComment == null)
            return null;


        Set<QuestionCommentInfoDto> questionCommentInfos = new LinkedHashSet<>(questionComment.size());
        for (QuestionComment comment : questionComment) {
            questionCommentInfos.add(getQuestionCommentInfo(comment));
        }
        return questionCommentInfos;
    }

    @Override
    public QuestionComment getQuestionComment(Long questionId, QuestionCommentDto questionCommentDto) {
        if (questionCommentDto == null)
            return null;

        return QuestionComment.builder()
                .body(questionCommentDto.getBody())
                .question(questionCommentDto.getQuestion())
                .build();
    }

    protected QuestionCommentInfoDto getQuestionCommentInfo(QuestionComment questionComment) {
        if (questionComment == null)
            return null;

        return QuestionCommentInfoDto.builder()
                .owner(usersMapper.getUserInfo(questionComment.getQuestion().getOwner()))
                .body(questionComment.getBody())
                .createDate(questionComment.getCreateDate())
                .modDate(questionComment.getModDate())
                .build();
    }
}
