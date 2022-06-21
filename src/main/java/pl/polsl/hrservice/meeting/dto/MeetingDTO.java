package pl.polsl.hrservice.meeting.dto;

import lombok.Builder;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.candidate.dto.CandidateDTO;
import pl.polsl.hrservice.meeting.enumerator.MeetingType;
import pl.polsl.hrservice.user.domain.User;
import pl.polsl.hrservice.user.dto.UserDTO;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public record MeetingDTO(
        Long id,
        UserDTO mainInterviewer,
        CandidateDTO candidate,
        Set<UserDTO> interviewers,
        MeetingType meetingType,
        ZonedDateTime dateTime
) {
    @Builder(toBuilder = true) public MeetingDTO {};
}
