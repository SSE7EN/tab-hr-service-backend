package pl.polsl.hrservice.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.hrservice.application.entity.ApplicationEntity;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public interface ApplicationJpaRepository extends JpaRepository<ApplicationEntity, Long> {
}
