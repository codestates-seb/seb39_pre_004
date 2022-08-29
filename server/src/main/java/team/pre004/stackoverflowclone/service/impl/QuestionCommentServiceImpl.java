package team.pre004.stackoverflowclone.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.post.repository.QuestionCommentRepository;
import team.pre004.stackoverflowclone.domain.post.repository.QuestionRepository;
import team.pre004.stackoverflowclone.domain.user.repository.UsersRepository;
import team.pre004.stackoverflowclone.dto.post.QuestionCommentDto;
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
    public QuestionComment save(Long questionId, QuestionCommentDto questionCommentDto) {

        QuestionCommentDto comment = QuestionCommentDto
                .builder()
                .body(questionCommentDto.getBody())
                .question(questionRepository.findById(questionId)
                        .orElseThrow())
                .build();

        return questionCommentRepository.save(comment.toEntity());
    }

    @Override
    public Optional<QuestionComment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long Id) {

    }
}
