package pl.polsl.hrservice.raport.domain;

import lombok.Builder;
import pl.polsl.hrservice.position.enumerated.ProgrammingLanguage;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public record ProgrammingLanguageOccurrences(
        ProgrammingLanguage programmingLanguage,
        Long occurrences
) {
    @Builder public ProgrammingLanguageOccurrences {}
}
