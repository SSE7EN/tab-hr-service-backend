package pl.polsl.hrservice.user.dto;

import lombok.Builder;

/**
 * Created by piotrswierzy on 25.03.2022
 */
public record UserCreateDTO(
        String email,
        String password,
        String firstName,
        String lastName
) {
    @Builder public UserCreateDTO{}
}
