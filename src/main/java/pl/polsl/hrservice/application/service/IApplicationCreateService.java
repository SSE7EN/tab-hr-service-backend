package pl.polsl.hrservice.application.service;

import pl.polsl.hrservice.application.command.ApplicationCreateCommand;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.common.domain.SecurityWrapper;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public interface IApplicationCreateService {
    Application create(SecurityWrapper<ApplicationCreateCommand> command);
}
