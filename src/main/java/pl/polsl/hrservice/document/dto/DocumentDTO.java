package pl.polsl.hrservice.document.dto;

import pl.polsl.hrservice.document.enumerator.DocumentType;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public record DocumentDTO(
        Long id,
        DocumentType documentType,
        String url
) {
}
