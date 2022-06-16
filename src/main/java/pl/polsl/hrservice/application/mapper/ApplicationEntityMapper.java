package pl.polsl.hrservice.application.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.application.entity.ApplicationEntity;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface ApplicationEntityMapper {
    Application map(ApplicationEntity entity, @Context CycleAvoidingMappingContext context);
    ApplicationEntity map(Application domain, @Context CycleAvoidingMappingContext context);
}
