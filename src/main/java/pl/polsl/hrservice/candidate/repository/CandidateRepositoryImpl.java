package pl.polsl.hrservice.candidate.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.candidate.entity.CandidateEntity;
import pl.polsl.hrservice.candidate.mapper.CandidateEntityMapperWrapper;
import pl.polsl.hrservice.candidate.query.CandidateQuery;

import java.util.Optional;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Component
@RequiredArgsConstructor
public class CandidateRepositoryImpl implements ICandidateRepository {
    private final CandidateJpaRepository repository;
    private final CandidateEntityMapperWrapper mapperWrapper;

    @Override
    public Candidate create(final Candidate candidate) {
        return mapperWrapper.map(
                repository.save(mapperWrapper.map(candidate))
        );
    }

    @Override
    public Optional<Candidate> read(final Long id) {
        return repository.findById(id)
                .map(mapperWrapper::map);
    }

    @Override
    public Optional<Candidate> read(final String email) {
        return repository.findByUserEmail(email)
                .map(mapperWrapper::map);
    }

    @Override
    public Page<Candidate> readAll(final CandidateQuery query, final Pageable pageable) {
        return repository.findAll(queryToSpec(query), pageable)
                .map(mapperWrapper::map);
    }

    Specification<CandidateEntity> queryToSpec(final CandidateQuery query){
        return null;
    }
}
