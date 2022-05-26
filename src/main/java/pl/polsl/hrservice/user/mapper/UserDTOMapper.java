package pl.polsl.hrservice.user.mapper;

import org.mapstruct.Mapper;
import pl.polsl.hrservice.user.Role;
import pl.polsl.hrservice.user.command.UserCreateCommand;
import pl.polsl.hrservice.user.command.UserUpdateCommand;
import pl.polsl.hrservice.user.domain.User;
import pl.polsl.hrservice.user.dto.UserCreateDTO;
import pl.polsl.hrservice.user.dto.UserDTO;
import pl.polsl.hrservice.user.dto.UserUpdateDTO;

/**
 * Created by piotrswierzy on 24.03.2022
 */
@Mapper
public interface UserDTOMapper {
    UserDTO map(User domain);
    UserCreateCommand map(UserCreateDTO dto, Role role);
    UserUpdateCommand map(UserUpdateDTO dto);
}
