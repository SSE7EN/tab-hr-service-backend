package pl.polsl.hrservice.user.service;

import pl.polsl.hrservice.user.command.UserCreateCommand;
import pl.polsl.hrservice.user.domain.User;

/**
 * Created by piotrswierzy on 25.03.2022
 */
public interface IUserCreateService {
    User create(UserCreateCommand command);
}
