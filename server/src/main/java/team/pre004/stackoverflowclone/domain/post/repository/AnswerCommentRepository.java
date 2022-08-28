package team.pre004.stackoverflowclone.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.pre004.stackoverflowclone.domain.post.entity.AnswerComment;

public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Long> {
}