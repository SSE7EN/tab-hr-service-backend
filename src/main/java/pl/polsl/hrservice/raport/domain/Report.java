package pl.polsl.hrservice.raport.domain;

import lombok.Builder;

import java.time.ZonedDateTime;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public record Report(
        ZonedDateTime generationTime,
        PositionInfo positionInfo
) {
    @Builder(toBuilder = true) public Report{}
}
