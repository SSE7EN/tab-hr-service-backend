package pl.polsl.hrservice.document.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;
import pl.polsl.hrservice.document.domain.Document;
import pl.polsl.hrservice.document.entity.DocumentEntity;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface DocumentEntityMapper {
    Document map(DocumentEntity entity, @Context CycleAvoidingMappingContext context);
    DocumentEntity map(Document domain, @Context CycleAvoidingMappingContext context);
}
