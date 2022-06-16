package pl.polsl.hrservice.position.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.hrservice.position.dto.PositionCreateDTO;
import pl.polsl.hrservice.position.dto.PositionDTO;
import pl.polsl.hrservice.position.dto.PositionUpdateDTO;
import pl.polsl.hrservice.position.mapper.PositionDTOMapper;
import pl.polsl.hrservice.position.query.PositionQuery;
import pl.polsl.hrservice.position.service.IPositionCreateService;
import pl.polsl.hrservice.position.service.IPositionReadService;
import pl.polsl.hrservice.position.service.IPositionUpdateService;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionDTOMapper mapper;
    private final IPositionCreateService positionCreateService;
    private final IPositionUpdateService positionUpdateService;
    private final IPositionReadService positionReadService;

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority(T(pl.polsl.hrservice.user.enumerator.Role).ADMIN)")
    @PostMapping
    public PositionDTO create(@RequestBody final PositionCreateDTO dto) {
        final var position = positionCreateService.create(mapper.map(dto));
        return mapper.map(position);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority(T(pl.polsl.hrservice.user.enumerator.Role).ADMIN)")
    @PutMapping("/{id}")
    public PositionDTO update(@PathVariable("id") final Long id,  final @RequestBody PositionUpdateDTO dto) {
        final var position = positionUpdateService.update(mapper.map(dto, id));
        return mapper.map(position);
    }

    @GetMapping("/{id}")
    public PositionDTO read(@PathVariable("id") final Long id) {
        final var position = positionReadService.read(id);
        return mapper.map(position);
    }

    @GetMapping
    public Page<PositionDTO> readAll(final PositionQuery query,
                                 @PageableDefault final Pageable pageable) {
        return positionReadService.readAll(query, pageable)
                .map(mapper::map);
    }
}
