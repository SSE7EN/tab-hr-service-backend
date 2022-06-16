package pl.polsl.hrservice.application.domain;

import lombok.Builder;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.document.domain.Document;
import pl.polsl.hrservice.position.domain.Position;

import java.util.List;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public record Application(
        Long id,
        String description,
        List<Document> documents,
        Position position,
        Candidate candidate
) {
    @Builder(toBuilder = true) public Application{}
}
