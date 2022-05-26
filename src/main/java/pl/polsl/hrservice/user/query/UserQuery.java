package pl.polsl.hrservice.user.query;

import lombok.Builder;

/**
 * Created by piotrswierzy on 24.03.2022
 */
public record UserQuery() {
    @Builder(toBuilder = true) public UserQuery{}
}
