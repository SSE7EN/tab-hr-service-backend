package pl.polsl.hrservice.candidate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.hrservice.candidate.command.CandidateCreateCommand;
import pl.polsl.hrservice.candidate.command.CandidateUpdateCommand;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.candidate.exception.CandidateNotFoundException;
import pl.polsl.hrservice.candidate.query.CandidateQuery;
import pl.polsl.hrservice.candidate.repository.ICandidateRepository;
import pl.polsl.hrservice.common.domain.SecurityWrapper;
import pl.polsl.hrservice.user.service.IUserCreateService;
import pl.polsl.hrservice.user.service.IUserUpdateService;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements ICandidateCreateService,
        ICandidateUpdateService,
        ICandidateReadService {
    private final ICandidateRepository candidateRepository;
    private final IUserCreateService userCreateService;
    private final IUserUpdateService userUpdateService;
    @Override
    @Transactional
    public Candidate create(final CandidateCreateCommand command) {
        final var user = userCreateService.create(command.userCreateCommand());
        return candidateRepository.create(
                Candidate.builder()
                        .user(user)
                        .build()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Candidate read(final Long id) {
        return candidateRepository.read(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Candidate> readAll(final CandidateQuery query, final Pageable pageable) {
        return candidateRepository.readAll(query, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Candidate read(final String email) {
        return candidateRepository.read(email)
                .orElseThrow(() -> new CandidateNotFoundException(email));
    }

    @Override
    @Transactional
    public Candidate update(final CandidateUpdateCommand command) {
        final var candidate = read(command.id());
        final var user = userUpdateService.update(SecurityWrapper.of(command.userUpdateCommand()));

        return candidate.toBuilder()
                .user(user)
                .build();
    }
}
