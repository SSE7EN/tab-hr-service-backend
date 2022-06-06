package pl.polsl.hrservice.document.command;

import lombok.Builder;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by piotrswierzy on 13.10.2021
 */
public record UploadMultipartFileCommand(MultipartFile file) {
    @Builder(toBuilder = true) public UploadMultipartFileCommand{}
}
