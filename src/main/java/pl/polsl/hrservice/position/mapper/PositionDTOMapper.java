package pl.polsl.hrservice.position.mapper;

import org.mapstruct.Mapper;
import pl.polsl.hrservice.position.command.PositionCreateCommand;
import pl.polsl.hrservice.position.command.PositionUpdateCommand;
import pl.polsl.hrservice.position.domain.Position;
import pl.polsl.hrservice.position.dto.PositionCreateDTO;
import pl.polsl.hrservice.position.dto.PositionDTO;
import pl.polsl.hrservice.position.dto.PositionUpdateDTO;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Mapper
public interface PositionDTOMapper {
    PositionDTO map(Position domain);
    PositionCreateCommand map(PositionCreateDTO dto);
    PositionUpdateCommand map(PositionUpdateDTO dto, Long id);
}
