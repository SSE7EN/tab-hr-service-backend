package pl.polsl.hrservice.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.application.query.ApplicationQuery;

import java.util.List;
import java.util.Optional;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public interface IApplicationRepository {
    Application save(Application application);
    Optional<Application> read(Long id);
    Optional<Application> readByIdAndCandidateId(Long id, Long candidateId);
    Page<Application> readAll(ApplicationQuery query, Pageable page);

    List<Application> readAllByCandidate(Long id);

}
