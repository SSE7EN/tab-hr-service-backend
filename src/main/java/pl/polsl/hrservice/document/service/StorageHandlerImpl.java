package pl.polsl.hrservice.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.polsl.hrservice.document.command.UploadMultipartFileCommand;
import pl.polsl.hrservice.document.response.StorageUploadFileResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by piotrswierzy on 13.10.2021
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StorageHandlerImpl implements StorageHandler {
    private final static String DESTINATION = "src/main/webapp/WEB-INF/images";

    @Override
    public StorageUploadFileResponse uploadPublic(UploadMultipartFileCommand command) throws IOException {
        //TODO validate if allowed
        return StorageUploadFileResponse.builder()
                .url(uploadPublic(command.file()))
                .build();
    }

    private String uploadPublic(final MultipartFile file) throws IOException {
        final var destination = new File(DESTINATION);
        final var name = UUID.randomUUID() +"."+ Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        final var path = Paths.get(destination.getPath(), name);

        Files.copy(file.getInputStream(), path);
        return name;
    }
}
