package team.pre004.stackoverflowclone.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.pre004.stackoverflowclone.domain.post.entity.AnswerComment;
@Repository
public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Long> {
}