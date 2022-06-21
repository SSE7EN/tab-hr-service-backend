package pl.polsl.hrservice.meeting.query;

import lombok.Builder;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public record MeetingQuery(
        String candidateEmail
) {
    @Builder(toBuilder = true) public MeetingQuery{}
}
