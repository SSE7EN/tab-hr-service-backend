package pl.polsl.hrservice.application.service;

import pl.polsl.hrservice.application.command.ApplicationStatusUpdateCommand;
import pl.polsl.hrservice.application.command.ApplicationUpdateCommand;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.common.domain.SecurityWrapper;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public interface IApplicationUpdateService {
    Application update(SecurityWrapper<ApplicationUpdateCommand> command);
    Application update(ApplicationStatusUpdateCommand command);
}
