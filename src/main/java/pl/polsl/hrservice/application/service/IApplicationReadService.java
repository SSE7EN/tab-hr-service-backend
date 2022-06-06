package pl.polsl.hrservice.application.service;

import pl.polsl.hrservice.application.domain.Application;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public interface IApplicationReadService {
    Application read(Long id);
}
