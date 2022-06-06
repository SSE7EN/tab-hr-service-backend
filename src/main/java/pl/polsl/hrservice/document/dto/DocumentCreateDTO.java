package pl.polsl.hrservice.document.dto;

import lombok.Builder;
import org.springframework.web.bind.annotation.RequestPart;
import pl.polsl.hrservice.document.enumerator.DocumentType;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public record DocumentCreateDTO(
        DocumentType documentType,
        Long applicationId

) {
    @Builder(toBuilder = true) public DocumentCreateDTO{};
}
