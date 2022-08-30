package team.pre004.stackoverflowclone.domain.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.pre004.stackoverflowclone.domain.tag.entity.TagList;

public interface TagListRepository extends JpaRepository<TagList, Long> {
}