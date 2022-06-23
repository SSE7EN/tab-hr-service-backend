package pl.polsl.hrservice.position.domain;

import lombok.Builder;
import pl.polsl.hrservice.position.enumerated.ProgrammingLanguage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record Position(
        Long id,
        String name,
        String description,
        Set<ProgrammingLanguage> programmingLanguages
) {
    @Builder(toBuilder = true) public Position{}

    public String getProgrammingLanguagesNames(){
        return new HashSet<>(programmingLanguages)
                .stream()
                .map(ProgrammingLanguage::name)
                .collect(Collectors.joining(","));

    }
}
