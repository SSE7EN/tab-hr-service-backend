package pl.polsl.hrservice.meeting.service;

import pl.polsl.hrservice.meeting.command.MeetingCreateCommand;
import pl.polsl.hrservice.meeting.domain.Meeting;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public interface IMeetingCreateService {
    Meeting create(MeetingCreateCommand command);
}
