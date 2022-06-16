package pl.polsl.hrservice.application.dto;

import lombok.Builder;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.candidate.dto.CandidateDTO;
import pl.polsl.hrservice.document.domain.Document;
import pl.polsl.hrservice.document.dto.DocumentDTO;
import pl.polsl.hrservice.position.domain.Position;
import pl.polsl.hrservice.position.dto.PositionDTO;

import java.util.List;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public record ApplicationDTO(
        Long id,
        String description,
        List<DocumentDTO> documents,
        PositionDTO position,
        CandidateDTO candidate
) {
    @Builder(toBuilder = true) public ApplicationDTO {}
}
