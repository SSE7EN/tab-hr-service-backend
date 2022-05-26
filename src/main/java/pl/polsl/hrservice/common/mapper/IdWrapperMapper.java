package pl.polsl.hrservice.common.mapper;

import org.mapstruct.Mapper;
import pl.polsl.hrservice.common.definition.IIdWrapper;
import pl.polsl.hrservice.common.domain.StorageId;

import java.util.Optional;

/**
 * Created by piotrswierzy on 24.03.2022
 */
@Mapper
public interface IdWrapperMapper {
    default StorageId map(final String id){
        return Optional.ofNullable(id)
                .map(StorageId::of)
                .orElse(null);
    }
    default String map(final IIdWrapper idWrapper){
        return Optional.ofNullable(idWrapper)
                .map(IIdWrapper::getId)
                .orElse(null);
    }
}
