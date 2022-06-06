package pl.polsl.hrservice.document.domain;

import lombok.Builder;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.document.enumerator.DocumentType;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public record Document(
        Long id,
        String url,
        DocumentType documentType,
        Application application
) {
    @Builder(toBuilder = true)
    public Document{}
}
