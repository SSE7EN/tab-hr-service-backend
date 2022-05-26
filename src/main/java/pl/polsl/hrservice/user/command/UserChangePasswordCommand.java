package pl.polsl.hrservice.user.command;

import lombok.Builder;

/**
 * Created by piotrswierzy on 01.04.2022
 */
public record UserChangePasswordCommand(
        String oldPassword,
        String newPassword
) {
    @Builder public UserChangePasswordCommand{}
}
