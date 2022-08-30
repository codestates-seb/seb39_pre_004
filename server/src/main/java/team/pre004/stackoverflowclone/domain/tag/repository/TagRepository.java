package team.pre004.stackoverflowclone.domain.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.pre004.stackoverflowclone.domain.tag.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}