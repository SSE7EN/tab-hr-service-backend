package pl.polsl.hrservice.application.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import pl.polsl.hrservice.application.command.ApplicationCreateCommand;
import pl.polsl.hrservice.application.command.ApplicationUpdateCommand;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.application.dto.ApplicationCreateDTO;
import pl.polsl.hrservice.application.dto.ApplicationDTO;
import pl.polsl.hrservice.application.dto.ApplicationUpdateDTO;
import pl.polsl.hrservice.application.entity.ApplicationEntity;
import pl.polsl.hrservice.candidate.mapper.CandidateDTOMapper;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;
import pl.polsl.hrservice.document.mapper.DocumentDTOMapper;
import pl.polsl.hrservice.position.mapper.PositionDTOMapper;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Mapper(builder = @Builder(disableBuilder = true),
        uses = {
                PositionDTOMapper.class,
                DocumentDTOMapper.class,
                CandidateDTOMapper.class
        })
public interface ApplicationDTOMapper {
    ApplicationDTO map(Application domain, @Context CycleAvoidingMappingContext context);
    ApplicationCreateCommand map(ApplicationCreateDTO dto);
    ApplicationUpdateCommand map(ApplicationUpdateDTO dto, Long id);
}
