package pl.polsl.hrservice.position.domain;

import lombok.Builder;
import pl.polsl.hrservice.position.enumerated.ProgrammingLanguage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record Position(
        Long id,
        String name,
        String description,
        List<ProgrammingLanguage> programmingLanguages
) {
    @Builder(toBuilder = true) public Position{}
}
