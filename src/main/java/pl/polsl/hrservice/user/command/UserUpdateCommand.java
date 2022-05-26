package pl.polsl.hrservice.user.command;

/**
 * Created by piotrswierzy on 25.03.2022
 */
public record UserUpdateCommand(
        String firstName,
        String lastName
) {
}
