package pl.polsl.hrservice.user.dto;

import pl.polsl.hrservice.user.enumerator.Role;

import java.time.ZonedDateTime;

/**
 * Created by piotrswierzy on 24.03.2022
 */
public record UserDTO(
        Long id,

        String email,
        String firstName,
        String lastName,

        ZonedDateTime createdOn,
        ZonedDateTime modifiedOn,
        ZonedDateTime lastLoginOn,
        ZonedDateTime activatedOn,

        Role role
) {
}
