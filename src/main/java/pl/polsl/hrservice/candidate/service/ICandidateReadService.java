package pl.polsl.hrservice.candidate.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.candidate.query.CandidateQuery;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public interface ICandidateReadService {
    Candidate read(Long id);
    Page<Candidate> readAll(CandidateQuery query, Pageable pageable);
    Candidate read(String email);
}
