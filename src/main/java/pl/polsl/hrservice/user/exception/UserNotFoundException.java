package pl.polsl.hrservice.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by piotrswierzy on 24.03.2022
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String email){
        super(String.format("User with email: %s was not found", email));
    }

    public UserNotFoundException(final Long id){
        super(String.format("User with id: %d was not found", id));
    }
}
