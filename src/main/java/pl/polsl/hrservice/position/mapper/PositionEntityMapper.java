package pl.polsl.hrservice.position.mapper;

import org.mapstruct.Mapper;
import pl.polsl.hrservice.position.domain.Position;
import pl.polsl.hrservice.position.entity.PositionEntity;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Mapper
public interface PositionEntityMapper {
    Position map(PositionEntity entity);
    PositionEntity map(Position domain);
}
