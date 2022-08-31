package team.pre004.stackoverflowclone.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.AnswerComment;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.repository.AnswerCommentRepository;
import team.pre004.stackoverflowclone.domain.post.repository.AnswerRepository;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.dto.post.request.AnswerCommentDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomLikesConflictException;
import team.pre004.stackoverflowclone.handler.exception.CustomNotContentItemException;
import team.pre004.stackoverflowclone.service.AnswerCommentService;
import team.pre004.stackoverflowclone.service.AnswerService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AnswerCommentServiceImpl implements AnswerCommentService {

    private final AnswerRepository answerRepository;
    private final AnswerCommentRepository answerCommentRepository;
    private final UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public Set<AnswerComment> findAllByAnswer(Long answerId) {
        return answerCommentRepository.findAllByAnswer(
                answerRepository.findById(answerId)
                        .orElseThrow()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Set<AnswerComment> findAllByUsers(Long usersId) {
        return answerCommentRepository.findAllByOwner(
                usersRepository.findById(usersId)
                        .orElseThrow()
        );
    }

    @Override
    @Transactional
    public AnswerComment save(AnswerComment answerComment) {
        return answerCommentRepository.save(answerComment);
    }

    @Override
    @Transactional
    public AnswerComment update(Long answerId, Long commentId, AnswerCommentDto answerCommentDto) {

        boolean isAnswerCommentForQuestion = answerCommentRepository
                .existsAnswerCommentsByAnswer(answerRepository.findById(answerId)
                        .orElseThrow(
                                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_ID)
                        ));

        if(isAnswerCommentForQuestion){
            answerCommentRepository.findById(commentId).ifPresentOrElse(
                    comment -> comment.update(answerCommentDto.getBody()),
                    () -> {
                        throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_COMMENT_ID);
                    }
            );
            return null;
        }
        else
            throw new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_COMMENT_ID);

    }

    @Override
    @Transactional(readOnly = true)
    public AnswerComment findById(Long id) {
        return answerCommentRepository.findById(id).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_ANSWER_COMMENT_ID)
        );
    }

    @Override
    @Transactional
    public void deleteById(Long answerId, Long commentId) {

        answerRepository.findById(answerId).orElseThrow(
                () -> new CustomNotContentItemException(ExceptionMessage.NOT_CONTENT_QUESTION_ID)
        );

        try {
            answerCommentRepository.deleteById(commentId);
        } catch (CustomNotContentItemException ex) {
            throw new CustomLikesConflictException(ExceptionMessage.NOT_CONTENT_ANSWER_COMMENT_ID);
        }
    }
}
