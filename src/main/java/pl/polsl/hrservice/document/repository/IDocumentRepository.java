package pl.polsl.hrservice.document.repository;

import pl.polsl.hrservice.document.domain.Document;

import java.util.List;
import java.util.Optional;

/**
 * Created by piotrswierzy on 06.06.2022
 */
public interface IDocumentRepository {
    Document create(Document document);
    Document save(Document document);
    Optional<Document> read(Long id);
    List<Document> readByApplication(Long id);
}
