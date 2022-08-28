package team.pre004.stackoverflowclone.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}