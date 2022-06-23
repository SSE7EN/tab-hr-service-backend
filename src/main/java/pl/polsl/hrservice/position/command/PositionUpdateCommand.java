package pl.polsl.hrservice.position.command;

import lombok.Builder;
import pl.polsl.hrservice.position.enumerated.ProgrammingLanguage;

import java.util.List;
import java.util.Set;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Builder
public record PositionUpdateCommand(
        Long id,
        String name,
        String description,
        Set<ProgrammingLanguage> programmingLanguages
) {
}
