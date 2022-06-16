package pl.polsl.hrservice.application.dto;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record ApplicationCreateDTO(
        Long id,
        Long positionId,
        String description
) {
}
