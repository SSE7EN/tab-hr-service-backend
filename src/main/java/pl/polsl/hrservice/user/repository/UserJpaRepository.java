package pl.polsl.hrservice.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.polsl.hrservice.user.entity.UserEntity;

import java.util.Optional;

/**
 * Created by piotrswierzy on 24.03.2022
 */
public interface UserJpaRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
}
