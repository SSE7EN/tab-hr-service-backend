package pl.polsl.hrservice.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.hrservice.document.entity.DocumentEntity;

import java.util.List;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public interface DocumentJpaRepository  extends JpaRepository<DocumentEntity, Long> {
    List<DocumentEntity> findAllByApplicationId(Long applicationId);
}
