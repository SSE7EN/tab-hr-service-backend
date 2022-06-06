package pl.polsl.hrservice.document.service;

import pl.polsl.hrservice.document.command.UploadMultipartFileCommand;
import pl.polsl.hrservice.document.response.StorageUploadFileResponse;

import java.io.IOException;

/**
 * Created by piotrswierzy on 13.10.2021
 */
public interface StorageHandler {
    StorageUploadFileResponse uploadPublic(final UploadMultipartFileCommand command) throws IOException;
}
