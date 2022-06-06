package pl.polsl.hrservice.application.mapper;

import org.mapstruct.Mapper;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.application.entity.ApplicationEntity;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@Mapper
public interface ApplicationEntityMapper {
    Application map(ApplicationEntity entity);
    ApplicationEntity map(Application domain);
}
