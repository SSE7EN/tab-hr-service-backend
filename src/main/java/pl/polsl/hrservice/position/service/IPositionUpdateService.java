package pl.polsl.hrservice.position.service;

import pl.polsl.hrservice.position.command.PositionUpdateCommand;
import pl.polsl.hrservice.position.domain.Position;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public interface IPositionUpdateService {
    Position update(PositionUpdateCommand command);
}
