package team.pre004.stackoverflowclone.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.AnswerLikeUp;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionLikeUp;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import java.util.Optional;

@Repository
public interface AnswerLikeUpRepository extends JpaRepository<AnswerLikeUp, Long> {
    Optional<AnswerLikeUp> findByAnswerAndOwner(Answer answer, Users owner);

}