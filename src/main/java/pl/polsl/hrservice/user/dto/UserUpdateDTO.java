package pl.polsl.hrservice.user.dto;

import lombok.Builder;

/**
 * Created by piotrswierzy on 25.03.2022
 */
public record UserUpdateDTO(
        String firstName,
        String lastName
) {
    @Builder public UserUpdateDTO{}
}
