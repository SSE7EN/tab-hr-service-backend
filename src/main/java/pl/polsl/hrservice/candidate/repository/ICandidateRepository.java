package pl.polsl.hrservice.candidate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.candidate.entity.CandidateEntity;
import pl.polsl.hrservice.candidate.query.CandidateQuery;

import java.util.List;
import java.util.Optional;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public interface ICandidateRepository {
    Candidate create(Candidate candidate);
    Optional<Candidate> read(Long id);
    Optional<Candidate> read(String email);
    Page<Candidate> readAll(CandidateQuery query, Pageable pageable);
}
