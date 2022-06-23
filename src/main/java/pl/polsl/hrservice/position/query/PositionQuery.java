package pl.polsl.hrservice.position.query;

import lombok.Builder;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record PositionQuery() {
    @Builder(toBuilder = true) public PositionQuery{}
}
