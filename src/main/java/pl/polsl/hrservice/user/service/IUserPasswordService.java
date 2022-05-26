package pl.polsl.hrservice.user.service;

import pl.polsl.hrservice.common.domain.SecurityWrapper;
import pl.polsl.hrservice.user.command.UserChangePasswordCommand;
import pl.polsl.hrservice.user.command.UserPasswordResetCommand;
import pl.polsl.hrservice.user.command.UserPasswordResetRequestCommand;

/**
 * Created by piotrswierzy on 01.04.2022
 */
public interface IUserPasswordService {
    void requestPasswordReset(UserPasswordResetRequestCommand command);
    void resetPassword(UserPasswordResetCommand command);
    void changePassword(SecurityWrapper<UserChangePasswordCommand> command);
}
