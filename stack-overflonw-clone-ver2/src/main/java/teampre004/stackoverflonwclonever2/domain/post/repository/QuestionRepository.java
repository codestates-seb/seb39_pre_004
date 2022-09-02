package teampre004.stackoverflonwclonever2.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teampre004.stackoverflonwclonever2.domain.post.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}