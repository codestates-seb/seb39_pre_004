package teampre004.stackoverflonwclonever2.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teampre004.stackoverflonwclonever2.domain.post.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}