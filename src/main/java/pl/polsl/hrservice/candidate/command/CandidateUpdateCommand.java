package pl.polsl.hrservice.candidate.command;

import pl.polsl.hrservice.user.command.UserUpdateCommand;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record CandidateUpdateCommand(
        Long id,
        UserUpdateCommand userUpdateCommand
) {
}
