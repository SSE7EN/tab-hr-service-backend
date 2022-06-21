package pl.polsl.hrservice.meeting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.hrservice.candidate.service.ICandidateReadService;
import pl.polsl.hrservice.meeting.command.MeetingCreateCommand;
import pl.polsl.hrservice.meeting.command.MeetingUpdateCommand;
import pl.polsl.hrservice.meeting.domain.Meeting;
import pl.polsl.hrservice.meeting.exception.MeetingNotFoundException;
import pl.polsl.hrservice.meeting.query.MeetingQuery;
import pl.polsl.hrservice.meeting.repository.IMeetingRepository;
import pl.polsl.hrservice.user.service.IUserReadService;

import java.util.stream.Collectors;

/**
 * Created by piotrswierzy on 21.06.2022
 */
@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements IMeetingReadService, IMeetingCreateService, IMeetingUpdateService {
    private final IUserReadService userReadService;
    private final ICandidateReadService candidateReadService;
    private final IMeetingRepository meetingRepository;

    @Override
    @Transactional(readOnly = true)
    public Meeting read(final Long id) {
        return meetingRepository.read(id)
                .orElseThrow(() -> new MeetingNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Meeting> readAll(final MeetingQuery query, final Pageable page) {
        return meetingRepository.readAll(query, page);
    }

    @Override
    @Transactional
    public Meeting create(final MeetingCreateCommand command) {
        final var mainInterviewer = userReadService.read(command.mainInterviewerId());
        final var interviewers = command.interviewersIds().stream()
                .map(userReadService::read)
                .collect(Collectors.toSet());
        final var candidate = candidateReadService.read(command.candidateId());
        return meetingRepository.create(Meeting.builder()
                .candidate(candidate)
                .mainInterviewer(mainInterviewer)
                .interviewers(interviewers)
                .meetingType(command.meetingType())
                .dateTime(command.dateTime())
                .build());
    }

    @Override
    @Transactional
    public Meeting update(final MeetingUpdateCommand command) {
        final var meeting = read(command.id());
        final var mainInterviewer = userReadService.read(command.mainInterviewerId());
        final var interviewers = command.interviewersIds().stream()
                .map(userReadService::read)
                .collect(Collectors.toSet());
        final var candidate = candidateReadService.read(command.candidateId());
        return meetingRepository.create(meeting.toBuilder()
                .candidate(candidate)
                .mainInterviewer(mainInterviewer)
                .interviewers(interviewers)
                .meetingType(command.meetingType())
                .dateTime(command.dateTime())
                .build());
    }
}
