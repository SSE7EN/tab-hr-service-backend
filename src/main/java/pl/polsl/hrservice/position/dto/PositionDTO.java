package pl.polsl.hrservice.position.dto;

import lombok.Builder;
import pl.polsl.hrservice.position.enumerated.ProgrammingLanguage;

import java.util.List;
import java.util.Set;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record PositionDTO(
        Long id,
        String name,
        String description,
        Set<ProgrammingLanguage> programmingLanguages
) {
    @Builder(toBuilder = true) public PositionDTO {}
}
