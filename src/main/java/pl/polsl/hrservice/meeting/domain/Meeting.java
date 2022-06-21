package pl.polsl.hrservice.meeting.domain;

import lombok.Builder;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.meeting.enumerator.MeetingType;
import pl.polsl.hrservice.user.domain.User;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public record Meeting(
        Long id,
        User mainInterviewer,
        Candidate candidate,
        Set<User> interviewers,
        MeetingType meetingType,
        ZonedDateTime dateTime
) {
    @Builder(toBuilder = true) public Meeting{};
}
