package team.pre004.stackoverflowclone.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.Question;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Set<Answer> findAllBy();
    Set<Answer> findAllByQuestion(Question question);
}