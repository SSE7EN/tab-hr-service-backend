package pl.polsl.hrservice.application.command;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record ApplicationCreateCommand(
        Long positionId,
        Long candidateId,
        String description
) {
}
