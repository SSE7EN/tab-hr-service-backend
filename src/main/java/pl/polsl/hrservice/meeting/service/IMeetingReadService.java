package pl.polsl.hrservice.meeting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.polsl.hrservice.meeting.domain.Meeting;
import pl.polsl.hrservice.meeting.query.MeetingQuery;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public interface IMeetingReadService {
    Meeting read(Long id);
    Page<Meeting> readAll(MeetingQuery query, Pageable page);
}
