package pl.polsl.hrservice.application.command;

import lombok.Builder;
import pl.polsl.hrservice.application.enumerator.ApplicationStatus;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.document.domain.Document;
import pl.polsl.hrservice.position.domain.Position;

import java.util.List;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record ApplicationUpdateCommand(
        Long id,
        Long positionId,
        String description
) {

}
