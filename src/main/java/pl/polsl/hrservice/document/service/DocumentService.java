package pl.polsl.hrservice.document.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.hrservice.application.service.IApplicationReadService;
import pl.polsl.hrservice.document.command.DocumentCreateCommand;
import pl.polsl.hrservice.document.command.DocumentUpdateCommand;
import pl.polsl.hrservice.document.domain.Document;
import pl.polsl.hrservice.document.exception.DocumentNotFoundException;
import pl.polsl.hrservice.document.repository.IDocumentRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Service
@RequiredArgsConstructor
public class DocumentService implements IDocumentCreateService, IDocumentUpdateService, IDocumentReadService{
    private final StorageHandler storageHandler;
    private final IDocumentRepository documentRepository;
    private final IApplicationReadService applicationReadService;

    @Override
    @Transactional
    public Document create(DocumentCreateCommand command) throws IOException {
        final var storageResponse = storageHandler.uploadPublic(
                command.uploadMultipartFileCommand());

        return documentRepository.create(
                Document.builder()
                        .url(storageResponse.url())
                        .build()
        );
    }

    @Override
    @Transactional
    public Document update(DocumentUpdateCommand command) throws IOException {
        final var document = read(command.id());
        final var application = applicationReadService.read(command.applicationId());
        return documentRepository.save(
                document.toBuilder()
                        .documentType(command.documentType())
                        .application(application)
                        .build()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Document read(Long id) {
        return documentRepository.read(id)
                .orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Document> readByApplication(final Long applicationId) {
        return documentRepository.readByApplication(applicationId);
    }
}
