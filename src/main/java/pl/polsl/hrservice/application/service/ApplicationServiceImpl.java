package pl.polsl.hrservice.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.hrservice.application.command.ApplicationCreateCommand;
import pl.polsl.hrservice.application.command.ApplicationUpdateCommand;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.application.enumerator.ApplicationStatus;
import pl.polsl.hrservice.application.exception.ApplicationNotFoundException;
import pl.polsl.hrservice.application.query.ApplicationQuery;
import pl.polsl.hrservice.application.repository.IApplicationRepository;
import pl.polsl.hrservice.candidate.service.ICandidateReadService;
import pl.polsl.hrservice.common.domain.SecurityWrapper;
import pl.polsl.hrservice.position.service.IPositionReadService;

import java.util.List;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements IApplicationReadService,
        IApplicationCreateService, IApplicationUpdateService {
    private final IApplicationRepository applicationRepository;
    private final IPositionReadService positionReadService;
    private final ICandidateReadService candidateReadService;
    @Override
    @Transactional(readOnly = true)
    public Application read(final Long id) {
        return applicationRepository.read(id)
                .orElseThrow(ApplicationNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Application readByIdAndCandidateId(final Long id, final Long candidateId) {
        return applicationRepository.readByIdAndCandidateId(id, candidateId)
                .orElseThrow(ApplicationNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Application> readAll(final ApplicationQuery query, final Pageable page) {
        return applicationRepository.readAll(query, page);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Application> readAll(String email) {
        final var candidate = candidateReadService.read(email);
        return applicationRepository.readAllByCandidate(candidate.id());
    }

    @Override
    @Transactional
    public Application create(final SecurityWrapper<ApplicationCreateCommand> commandWrapper) {
        final var command = commandWrapper.command();
        final var candidate = candidateReadService.read(commandWrapper.currentUserEmail());
        final var position = positionReadService.read(command.positionId());
        return applicationRepository.save(
                Application.builder()
                        .candidate(candidate)
                        .position(position)
                        .description(command.description())
                        .status(ApplicationStatus.IN_PROGRESS)
                        .build()
        );
    }

    @Override
    @Transactional
    public Application update(SecurityWrapper<ApplicationUpdateCommand> commandWrapper) {
        final var command = commandWrapper.command();
        final var candidate = candidateReadService.read(commandWrapper.currentUserEmail());
        final var application = readByIdAndCandidateId(command.id(), candidate.id());
        final var position = positionReadService.read(command.positionId());
        return applicationRepository.save(
                application.toBuilder()
                        .position(position)
                        .description(command.description())
                        .status(command.status())
                        .build()
        );
    }
}
