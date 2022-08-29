package team.pre004.stackoverflowclone.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.pre004.stackoverflowclone.domain.post.entity.QuestionComment;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
}