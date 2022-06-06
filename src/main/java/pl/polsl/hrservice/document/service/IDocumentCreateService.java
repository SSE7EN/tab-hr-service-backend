package pl.polsl.hrservice.document.service;

import pl.polsl.hrservice.document.command.DocumentCreateCommand;
import pl.polsl.hrservice.document.domain.Document;

import java.io.IOException;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public interface IDocumentCreateService {
    Document create(DocumentCreateCommand command) throws IOException;
}
