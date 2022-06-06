package pl.polsl.hrservice.document.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.hrservice.document.domain.Document;
import pl.polsl.hrservice.document.mapper.DocumentEntityMapper;
import pl.polsl.hrservice.document.mapper.DocumentEntityMapperWrapper;

import java.util.List;
import java.util.Optional;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Service
@RequiredArgsConstructor
public class DocumentRepositoryImpl implements IDocumentRepository {
    private final DocumentJpaRepository documentJpaRepository;
    private final DocumentEntityMapperWrapper mapper;
    @Override
    public Document create(Document document) {
        return mapper.map(
                documentJpaRepository.save(
                        mapper.map(document)
                )
        );
    }

    @Override
    public Optional<Document> read(Long id) {
        return documentJpaRepository.findById(id)
                .map(mapper::map);

    }

    @Override
    public List<Document> readByApplication(Long id) {
        return null;
    }
}
