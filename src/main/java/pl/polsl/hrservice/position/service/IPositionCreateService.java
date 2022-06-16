package pl.polsl.hrservice.position.service;

import pl.polsl.hrservice.position.command.PositionCreateCommand;
import pl.polsl.hrservice.position.domain.Position;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public interface IPositionCreateService {
    Position create(PositionCreateCommand command);
}
