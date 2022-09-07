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
import team.pre004.stackoverflowclone.handler.exception.CustomLikesConflictException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.service.QuestionCommentService;

import java.util.List;
import java.util.Set;


@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionCommentServiceImpl implements QuestionCommentService {

    private final QuestionRepository questionRepository;
    private final QuestionCommentRepository questionCommentRepository;
    private final UsersRepository usersRepository;


    @Override
    @Transactional(readOnly = true)
    public Set<QuestionComment> findAllByQuestion(Long questionId) {

        return questionCommentRepository.findAllByQuestion(
                questionRepository.findById(questionId)
                        .orElseThrow()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Set<QuestionComment> findAllByUsers(Long usersId) {

        return questionCommentRepository.findAllByOwner(
                usersRepository.findById(usersId)
                        .orElseThrow()
        );
    }

    @Override
    @Transactional
    public QuestionComment save(QuestionComment questionComment) {

        return questionCommentRepository.save(questionComment);
    }

    @Override
    @Transactional
    public QuestionComment update(Long questionId, Long commentId,  QuestionCommentDto questionCommentDto) {

        boolean isQuestionCommentForQuestion = questionCommentRepository
                .existsQuestionCommentsByQuestion(questionRepository.findById(questionId)
                .orElseThrow(
                        () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
                ));

        if(isQuestionCommentForQuestion){
             questionCommentRepository.findById(commentId).ifPresentOrElse(
                    comment -> comment.update(questionCommentDto.getBody()),
                    () -> {
                        throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_COMMENT_ID);
                    }
            );
            return null;
        }
        else
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_COMMENT_ID);


    }

    @Override
    public QuestionComment findById(Long id) {

        return questionCommentRepository.findById(id).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_COMMENT_ID)
        );


    }

    @Override
    public void deleteById(Long questionId, Long commentId) {

        questionRepository.findById(questionId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );

        try {
            questionCommentRepository.deleteById(commentId);
        } catch (CustomNotContentItemException ex) {
            throw new CustomLikesConflictException(ExceptionMessage.NOT_CONTENT_QUESTION_COMMENT_ID);
        }
    }
}
