package pl.polsl.hrservice.user.dto;

import lombok.Builder;

import javax.validation.constraints.NotNull;

/**
 * Created by piotrswierzy on 01.04.2022
 */
public record UserPasswordResetRequestDTO(
        @NotNull
        String email
) {
        @Builder public UserPasswordResetRequestDTO{}
}
