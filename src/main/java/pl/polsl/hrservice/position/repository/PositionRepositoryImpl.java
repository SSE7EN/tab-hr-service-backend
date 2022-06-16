package pl.polsl.hrservice.position.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.position.domain.Position;
import pl.polsl.hrservice.position.entity.PositionEntity;
import pl.polsl.hrservice.position.mapper.PositionEntityMapper;
import pl.polsl.hrservice.position.query.PositionQuery;

import java.util.Optional;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Component
@RequiredArgsConstructor
public class PositionRepositoryImpl implements IPositionRepository {
    private final PositionEntityMapper mapper;
    private final PositionJpaRepository repository;

    @Override
    public Position save(final Position position) {
        return mapper.map(
                repository.save(
                        mapper.map(position)
                )
        );
    }

    @Override
    public Optional<Position> read(final Long id) {
        return repository.findById(id)
                .map(mapper::map);
    }

    @Override
    public Page<Position> readAll(PositionQuery query, Pageable page) {
        return repository.findAll(queryToSpec(query), page)
                .map(mapper::map);
    }

    private Specification<PositionEntity> queryToSpec(final PositionQuery query){
        return null;
    }
}
