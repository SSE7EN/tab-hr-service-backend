package pl.polsl.hrservice.document.service;

import pl.polsl.hrservice.document.command.DocumentUpdateCommand;
import pl.polsl.hrservice.document.domain.Document;

import java.io.IOException;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public interface IDocumentUpdateService {
    Document update(DocumentUpdateCommand command) throws IOException;
}
