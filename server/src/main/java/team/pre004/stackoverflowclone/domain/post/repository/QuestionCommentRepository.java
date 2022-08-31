package team.pre004.stackoverflowclone.domain.post.repository;

import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
    Set<QuestionComment> findAllByQuestion(Question question);
    Set<QuestionComment> findAllByOwner(Users users);

    boolean existsQuestionCommentsByQuestion(Question question);
}