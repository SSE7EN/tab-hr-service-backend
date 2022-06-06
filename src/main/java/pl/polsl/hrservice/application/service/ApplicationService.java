package pl.polsl.hrservice.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.application.exception.ApplicationNotFoundException;
import pl.polsl.hrservice.application.repository.IApplicationRepository;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Service
@RequiredArgsConstructor
public class ApplicationService implements IApplicationReadService {
    private final IApplicationRepository applicationRepository;
    @Override
    @Transactional(readOnly = true)
    public Application read(final Long id) {
        return applicationRepository.read(id)
                .orElseThrow(ApplicationNotFoundException::new);
    }
}
