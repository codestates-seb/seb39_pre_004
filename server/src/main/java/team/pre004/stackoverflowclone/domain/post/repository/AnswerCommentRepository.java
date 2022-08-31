package team.pre004.stackoverflowclone.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.AnswerComment;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import java.util.List;

@Repository
public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Long> {

    List<AnswerComment> findAllByAnswer(Answer answer);
    List<AnswerComment> findAllByOwner(Users owner);

    boolean existsAnswerCommentsByAnswer(Answer answer);

}