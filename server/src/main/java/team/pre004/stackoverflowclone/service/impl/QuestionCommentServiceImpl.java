package team.pre004.stackoverflowclone.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.post.repository.QuestionCommentRepository;
import team.pre004.stackoverflowclone.domain.post.repository.QuestionRepository;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.dto.post.request.QuestionCommentDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointItemsExeption;
import team.pre004.stackoverflowclone.service.QuestionCommentService;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionCommentServiceImpl implements QuestionCommentService {

    private final QuestionRepository questionRepository;
    private final QuestionCommentRepository questionCommentRepository;
    private final UsersRepository usersRepository;


    @Override
    public List<QuestionComment> findAllByQuestion(Long questionId) {

        return questionCommentRepository.findAllByQuestion(
                questionRepository.findById(questionId)
                        .orElseThrow()
        );
    }

    @Override
    public List<QuestionComment> findAllByUsers(Long usersId) {
        return null;
    }

    @Override
    @Transactional
    public QuestionComment save(QuestionComment questionComment) {

        return questionCommentRepository.save(questionComment);
    }

    @Override
    @Transactional
    public QuestionComment update(Long commentId, QuestionComment questionComment) {

        QuestionComment updateComment = questionCommentRepository.findById(commentId).orElseThrow(
                () -> new CustomNullPointItemsExeption(ExceptionMessage.NOT_CONTENT_QUESTION_COMMENT_ID)
        );

        updateComment.update(questionComment.getBody());

        return updateComment;
    }

    @Override
    public Optional<QuestionComment> findById(Long id) {

        return Optional.empty();
    }

    @Override
    public void deleteById(Long Id) {

    }
}
