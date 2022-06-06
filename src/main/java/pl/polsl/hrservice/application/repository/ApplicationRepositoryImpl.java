package pl.polsl.hrservice.application.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.application.mapper.ApplicationEntityMapper;

import java.util.Optional;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Service
@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements IApplicationRepository {
    private final ApplicationJpaRepository applicationJpaRepository;
    private final ApplicationEntityMapper mapper;
    @Override
    public Optional<Application> read(final Long id) {
        return applicationJpaRepository.findById(id)
                .map(mapper::map);

    }
}
