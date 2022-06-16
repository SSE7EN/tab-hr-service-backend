package pl.polsl.hrservice.candidate.command;

import lombok.Builder;
import pl.polsl.hrservice.user.command.UserCreateCommand;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Builder
public record CandidateCreateCommand(
        UserCreateCommand userCreateCommand
) {
}
