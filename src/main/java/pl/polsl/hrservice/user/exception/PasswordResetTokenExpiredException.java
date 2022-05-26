package pl.polsl.hrservice.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by piotrswierzy on 01.04.2022
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class PasswordResetTokenExpiredException extends RuntimeException{

    public PasswordResetTokenExpiredException(final String message){
        super(message);
    }

    public PasswordResetTokenExpiredException(){
        super("Token expired");
    }
}
