package team.pre004.stackoverflowclone.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.Question;

import java.util.Set;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Set<Answer> findAllBy();
}