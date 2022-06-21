package pl.polsl.hrservice.meeting.dto;

import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.meeting.enumerator.MeetingType;
import pl.polsl.hrservice.user.domain.User;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public record MeetingUpdateDTO(
        Long mainInterviewerId,
        Long candidateId,
        Set<Long> interviewersIds,
        MeetingType meetingType,
        ZonedDateTime dateTime
) {
}
