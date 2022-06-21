package pl.polsl.hrservice.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.polsl.hrservice.meeting.entity.MeetingEntity;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public interface MeetingJpaRepository extends JpaRepository<MeetingEntity, Long>, JpaSpecificationExecutor<MeetingEntity> {
}
