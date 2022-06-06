package pl.polsl.hrservice.user.command;

import lombok.Builder;
import pl.polsl.hrservice.user.enumerator.Role;

/**
 * Created by piotrswierzy on 25.03.2022
 */
public record UserCreateCommand(
        String email,
        String password,
        String firstName,
        String lastName,

        Role role
) {
    @Builder public UserCreateCommand{}
}
