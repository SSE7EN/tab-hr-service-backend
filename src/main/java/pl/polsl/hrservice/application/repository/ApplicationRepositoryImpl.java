package pl.polsl.hrservice.application.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.application.entity.ApplicationEntity;
import pl.polsl.hrservice.application.mapper.ApplicationEntityMapper;
import pl.polsl.hrservice.application.mapper.ApplicationEntityMapperWrapper;
import pl.polsl.hrservice.application.query.ApplicationQuery;

import java.util.Optional;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Service
@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements IApplicationRepository {
    private final ApplicationJpaRepository applicationJpaRepository;
    private final ApplicationEntityMapperWrapper mapperWrapper;

    @Override
    public Application save(final Application application) {
        return mapperWrapper.map(
                applicationJpaRepository.save(
                        mapperWrapper.map(application)
                )
        );
    }

    @Override
    public Optional<Application> read(final Long id) {
        return applicationJpaRepository.findById(id)
                .map(mapperWrapper::map);
    }

    @Override
    public Optional<Application> readByIdAndCandidateId(final Long id, final Long candidateId) {
        return applicationJpaRepository.findByIdAndAndCandidateId(id, candidateId)
                .map(mapperWrapper::map);
    }

    @Override
    public Page<Application> readAll(final ApplicationQuery query, final Pageable page) {
        return applicationJpaRepository.findAll(queryToSpec(query), page)
                .map(mapperWrapper::map);
    }

    private Specification<ApplicationEntity> queryToSpec(final ApplicationQuery query){
        return null;
    }
}
