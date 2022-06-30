package pl.polsl.hrservice.application.dto;

import lombok.Builder;
import pl.polsl.hrservice.application.enumerator.ApplicationStatus;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record ApplicationStatusUpdateDTO(
        ApplicationStatus status
){
}
