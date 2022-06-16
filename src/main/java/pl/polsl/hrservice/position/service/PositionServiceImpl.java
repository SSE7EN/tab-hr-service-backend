package pl.polsl.hrservice.position.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.hrservice.position.command.PositionCreateCommand;
import pl.polsl.hrservice.position.command.PositionUpdateCommand;
import pl.polsl.hrservice.position.domain.Position;
import pl.polsl.hrservice.position.exception.PositionNotFoundException;
import pl.polsl.hrservice.position.query.PositionQuery;
import pl.polsl.hrservice.position.repository.IPositionRepository;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements IPositionCreateService, IPositionUpdateService,
        IPositionReadService {
    private final IPositionRepository positionRepository;

    @Override
    @Transactional
    public Position create(final PositionCreateCommand command) {
        return positionRepository.save(
                Position.builder()
                        .name(command.name())
                        .description(command.description())
                        .programmingLanguages(command.programmingLanguages())
                        .build()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Position read(final Long id) {
        return positionRepository.read(id)
                .orElseThrow(() -> new PositionNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Position> readAll(final PositionQuery query, final Pageable page) {
        return positionRepository.readAll(query, page);
    }

    @Override
    @Transactional
    public Position update(final PositionUpdateCommand command) {
        final var position = read(command.id());
        return positionRepository.save(
                position.toBuilder()
                        .name(command.name())
                        .description(command.description())
                        .programmingLanguages(command.programmingLanguages())
                        .build()
        );
    }
}
