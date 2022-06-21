package pl.polsl.hrservice.meeting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.polsl.hrservice.meeting.domain.Meeting;
import pl.polsl.hrservice.meeting.query.MeetingQuery;

import java.util.Optional;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public interface IMeetingRepository {
    Meeting create(Meeting meeting);
    Optional<Meeting> read(Long id);
    Page<Meeting> readAll(MeetingQuery query, Pageable pageable);
}
