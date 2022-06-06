package pl.polsl.hrservice.document.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DocumentNotFoundException extends RuntimeException{
}
