package pl.polsl.hrservice.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.application.query.ApplicationQuery;

import java.util.List;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public interface IApplicationReadService {
    Application read(Long id);
    Application readByIdAndCandidateId(Long id, Long candidateId);
    Page<Application> readAll(ApplicationQuery query, Pageable page);
    List<Application> readAll(String email);
}
