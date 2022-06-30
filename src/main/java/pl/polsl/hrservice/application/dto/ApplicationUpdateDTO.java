package pl.polsl.hrservice.application.dto;

import pl.polsl.hrservice.application.enumerator.ApplicationStatus;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record ApplicationUpdateDTO(
        Long positionId,
        String description,
        ApplicationStatus status
) {
}
