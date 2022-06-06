package pl.polsl.hrservice.document.service;

import pl.polsl.hrservice.document.domain.Document;

import java.util.List;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public interface IDocumentReadService {
    Document read(Long id);
    List<Document> readByApplication(Long applicationId);
}
