package team.pre004.stackoverflowclone.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.pre004.stackoverflowclone.domain.user.entity.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByOwnerId(Users users);
    Optional<Users> findByName(String users);
    Optional<Users> findByEmail(String email);

}