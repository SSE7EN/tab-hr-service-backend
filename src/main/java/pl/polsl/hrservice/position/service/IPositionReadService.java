package pl.polsl.hrservice.position.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.polsl.hrservice.position.domain.Position;
import pl.polsl.hrservice.position.query.PositionQuery;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public interface IPositionReadService {
    Position read(Long id);
    Page<Position> readAll(PositionQuery query, Pageable page);
}
