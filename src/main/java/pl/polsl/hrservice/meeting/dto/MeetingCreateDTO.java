package pl.polsl.hrservice.meeting.dto;

import pl.polsl.hrservice.meeting.enumerator.MeetingType;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public record MeetingCreateDTO(
        Long mainInterviewerId,
        Long candidateId,
        Set<Long> interviewersIds,
        MeetingType meetingType,
        ZonedDateTime dateTime
) {
}
