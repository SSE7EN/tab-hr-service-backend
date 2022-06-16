package pl.polsl.hrservice.position.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.polsl.hrservice.position.entity.PositionEntity;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public interface PositionJpaRepository extends JpaRepository<PositionEntity, Long>,
        JpaSpecificationExecutor<PositionEntity> {
}
