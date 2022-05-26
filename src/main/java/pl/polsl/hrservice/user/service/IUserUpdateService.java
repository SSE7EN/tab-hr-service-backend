package pl.polsl.hrservice.user.service;

import pl.polsl.hrservice.common.domain.SecurityWrapper;
import pl.polsl.hrservice.user.command.UserUpdateCommand;
import pl.polsl.hrservice.user.domain.User;

/**
 * Created by piotrswierzy on 25.03.2022
 */
public interface IUserUpdateService {
    User update(SecurityWrapper<UserUpdateCommand> command);
    User update(User user);
}
