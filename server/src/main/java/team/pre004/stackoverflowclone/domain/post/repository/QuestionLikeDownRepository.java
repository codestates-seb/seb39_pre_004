package team.pre004.stackoverflowclone.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.pre004.stackoverflowclone.domain.post.entity.Question;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionLikeDown;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionLikeUp;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import java.util.Optional;

@Repository
public interface QuestionLikeDownRepository extends JpaRepository<QuestionLikeDown, Long> {
    Optional<QuestionLikeDown> findByQuestionAndOwner(Question question, Users owner);
}