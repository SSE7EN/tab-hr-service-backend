package pl.polsl.hrservice.candidate.domain;

import lombok.Builder;
import pl.polsl.hrservice.application.domain.Application;
import pl.polsl.hrservice.user.domain.User;

import java.util.List;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Builder
public record Candidate(
        Long id,
        List<Application> applications,
        User user
) {
    @Builder(toBuilder = true) public Candidate{};
}
