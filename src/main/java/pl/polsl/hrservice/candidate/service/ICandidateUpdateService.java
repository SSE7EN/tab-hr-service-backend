package pl.polsl.hrservice.candidate.service;

import pl.polsl.hrservice.candidate.command.CandidateUpdateCommand;
import pl.polsl.hrservice.candidate.domain.Candidate;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public interface ICandidateUpdateService {
    Candidate update(CandidateUpdateCommand command);
}
