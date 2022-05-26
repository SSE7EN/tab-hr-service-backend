package pl.polsl.hrservice.user.command;

import lombok.Builder;

import java.util.UUID;

/**
 * Created by piotrswierzy on 01.04.2022
 */
public record UserPasswordResetCommand(
        String email,
        UUID token,
        String password
) {
    @Builder public UserPasswordResetCommand{}
}
