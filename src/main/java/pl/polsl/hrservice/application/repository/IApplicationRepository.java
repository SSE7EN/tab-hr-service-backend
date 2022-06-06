package pl.polsl.hrservice.application.repository;

import pl.polsl.hrservice.application.domain.Application;

import java.util.Optional;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public interface IApplicationRepository {
    Optional<Application> read(Long id);
}
