package pl.polsl.hrservice.user.dto;

import lombok.Builder;

/**
 * Created by piotrswierzy on 01.04.2022
 */
public record UserChangePasswordDTO(
     String oldPassword,
     String newPassword
) {
    @Builder public UserChangePasswordDTO{}
}
