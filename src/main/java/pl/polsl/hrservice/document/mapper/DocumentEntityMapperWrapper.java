package pl.polsl.hrservice.document.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;
import pl.polsl.hrservice.document.domain.Document;
import pl.polsl.hrservice.document.entity.DocumentEntity;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Component
@RequiredArgsConstructor
public class DocumentEntityMapperWrapper {
    private final DocumentEntityMapper mapper;

    public DocumentEntity map(final Document domain){
        return mapper.map(domain, new CycleAvoidingMappingContext());
    }

    public Document map(final DocumentEntity entity){
        return mapper.map(entity, new CycleAvoidingMappingContext());
    }
}
