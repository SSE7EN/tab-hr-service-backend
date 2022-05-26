package pl.polsl.hrservice.user.mapper;

import org.mapstruct.Mapper;
import pl.polsl.hrservice.user.domain.User;
import pl.polsl.hrservice.user.entity.UserEntity;

/**
 * Created by piotrswierzy on 24.03.2022
 */
@Mapper
public interface UserEntityMapper {
    UserEntity map(User domain);
    User map(UserEntity entity);
}
