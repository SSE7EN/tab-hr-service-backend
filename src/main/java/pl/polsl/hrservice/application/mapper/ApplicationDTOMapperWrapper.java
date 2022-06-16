package pl.polsl.hrservice.application.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.application.dto.ApplicationDTO;
import pl.polsl.hrservice.application.entity.ApplicationEntity;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Component
@RequiredArgsConstructor
public class ApplicationDTOMapperWrapper {
    private final ApplicationDTOMapper mapper;

    public ApplicationDTO map(final Application domain){
        return mapper.map(domain, new CycleAvoidingMappingContext());
    }

}
