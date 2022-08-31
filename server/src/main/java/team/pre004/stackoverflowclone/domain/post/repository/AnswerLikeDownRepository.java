package team.pre004.stackoverflowclone.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.pre004.stackoverflowclone.domain.post.entity.Answer;
import team.pre004.stackoverflowclone.domain.post.entity.AnswerLikeDown;
import team.pre004.stackoverflowclone.domain.post.entity.AnswerLikeUp;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import java.util.Optional;

@Repository
public interface AnswerLikeDownRepository extends JpaRepository<AnswerLikeDown, Long> {
    Optional<AnswerLikeDown> findByAnswerAndOwner(Answer answer, Users owner);

}