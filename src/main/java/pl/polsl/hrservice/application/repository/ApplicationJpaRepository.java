package pl.polsl.hrservice.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.polsl.hrservice.application.entity.ApplicationEntity;

import javax.persistence.Lob;
import java.util.Optional;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public interface ApplicationJpaRepository extends JpaRepository<ApplicationEntity, Long>,
        JpaSpecificationExecutor<ApplicationEntity> {

    Optional<ApplicationEntity> findByIdAndAndCandidateId(Long id, Long candidateId);
}
