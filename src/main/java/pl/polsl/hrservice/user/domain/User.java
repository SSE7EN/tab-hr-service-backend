package pl.polsl.hrservice.user.domain;

import lombok.Builder;
import pl.polsl.hrservice.user.enumerator.Role;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Created by piotrswierzy on 17.05.2022
 */
public record User(
        Long id,

        String email,
        String password,
        String firstName,
        String lastName,

        ZonedDateTime createdOn,
        ZonedDateTime modifiedOn,
        ZonedDateTime lastLoginOn,
        ZonedDateTime activatedOn,

        UUID passwordResetToken,
        ZonedDateTime passwordResetTokenGenerationTime,

        boolean activated,
        Role role
) {
    @Builder(toBuilder = true) public User{}


    public boolean isValid(){
        return activated;
    }
}
