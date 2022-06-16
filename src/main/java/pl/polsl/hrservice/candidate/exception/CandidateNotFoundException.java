package pl.polsl.hrservice.candidate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by piotrswierzy on 24.03.2022
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(final String email){
        super(String.format("Candidate with email: %s was not found", email));
    }

    public CandidateNotFoundException(final Long id){
        super(String.format("Candidate with id: %d was not found", id));
    }
}
