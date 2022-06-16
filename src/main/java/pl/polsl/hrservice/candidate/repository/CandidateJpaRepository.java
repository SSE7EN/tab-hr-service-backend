package pl.polsl.hrservice.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.polsl.hrservice.candidate.entity.CandidateEntity;

import java.util.Optional;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public interface CandidateJpaRepository extends JpaRepository<CandidateEntity, Long>, JpaSpecificationExecutor<CandidateEntity> {
    Optional<CandidateEntity> findByUserEmail(String email);
}
