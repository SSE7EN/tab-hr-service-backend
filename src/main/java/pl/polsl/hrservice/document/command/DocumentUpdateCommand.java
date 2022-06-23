package pl.polsl.hrservice.document.command;

import lombok.Builder;
import pl.polsl.hrservice.document.enumerator.DocumentType;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public record DocumentUpdateCommand(
        Long id,
        UploadMultipartFileCommand uploadMultipartFileCommand,
        DocumentType documentType,
        Long applicationId
) {
    @Builder(toBuilder = true) public DocumentUpdateCommand {}
}
