package pl.dalke.issue_tracker_api.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByLogin(String login);
    Optional<UserEntity> findByLogin(String login);
}
