package pl.polsl.hrservice.application.command;

import lombok.Builder;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record ApplicationCreateCommand(
        Long positionId,
        Long candidateId,
        String description
) {
    @Builder(toBuilder = true) public ApplicationCreateCommand{}
}
