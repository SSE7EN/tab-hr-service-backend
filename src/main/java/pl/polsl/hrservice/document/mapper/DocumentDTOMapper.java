package pl.polsl.hrservice.document.mapper;

import org.mapstruct.Mapper;
import pl.polsl.hrservice.document.domain.Document;
import pl.polsl.hrservice.document.dto.DocumentDTO;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Mapper
public interface DocumentDTOMapper {
    DocumentDTO map(Document domain);
}
