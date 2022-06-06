package pl.polsl.hrservice.application.domain;

import pl.polsl.hrservice.document.domain.Document;

import java.util.List;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public record Application(
        Long id,
        List<Document> documents
) {
}
