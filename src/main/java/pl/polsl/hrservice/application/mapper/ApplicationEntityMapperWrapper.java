package pl.polsl.hrservice.application.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.application.entity.ApplicationEntity;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;
import pl.polsl.hrservice.document.domain.Document;
import pl.polsl.hrservice.document.entity.DocumentEntity;
import pl.polsl.hrservice.document.mapper.DocumentEntityMapper;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Component
@RequiredArgsConstructor
public class ApplicationEntityMapperWrapper {
    private final ApplicationEntityMapper mapper;

    public ApplicationEntity map(final Application domain){
        return mapper.map(domain, new CycleAvoidingMappingContext());
    }

    public Application map(final ApplicationEntity entity){
        return mapper.map(entity, new CycleAvoidingMappingContext());
    }
}
