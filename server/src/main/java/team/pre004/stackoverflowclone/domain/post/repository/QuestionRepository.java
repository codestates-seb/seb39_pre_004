package team.pre004.stackoverflowclone.domain.post.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.pre004.stackoverflowclone.domain.post.entity.Question;

import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Modifying
    @Query(value = "update Question q set q.view = q.view + 1 where q.questionId = :id")
    int updateView(Long id);
    Set<Question> findAllBy();
}