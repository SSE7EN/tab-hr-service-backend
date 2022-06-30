package pl.polsl.hrservice.application.command;

import lombok.Builder;
import pl.polsl.hrservice.application.enumerator.ApplicationStatus;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record ApplicationStatusUpdateCommand(
        Long id,
        ApplicationStatus status
) {
    @Builder(toBuilder = true) public ApplicationStatusUpdateCommand{}

}
