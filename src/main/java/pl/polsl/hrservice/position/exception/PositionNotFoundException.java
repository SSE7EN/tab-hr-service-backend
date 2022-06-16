package pl.polsl.hrservice.position.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by piotrswierzy on 24.03.2022
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(final Long id){
        super(String.format("Position with id: %d was not found", id));
    }
}
