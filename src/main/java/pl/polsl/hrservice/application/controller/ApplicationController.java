package pl.polsl.hrservice.application.controller;

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
import pl.polsl.hrservice.application.dto.ApplicationCreateDTO;
import pl.polsl.hrservice.application.dto.ApplicationDTO;
import pl.polsl.hrservice.application.dto.ApplicationUpdateDTO;
import pl.polsl.hrservice.application.mapper.ApplicationDTOMapper;
import pl.polsl.hrservice.application.mapper.ApplicationDTOMapperWrapper;
import pl.polsl.hrservice.application.query.ApplicationQuery;
import pl.polsl.hrservice.application.service.IApplicationCreateService;
import pl.polsl.hrservice.application.service.IApplicationReadService;
import pl.polsl.hrservice.application.service.IApplicationUpdateService;
import pl.polsl.hrservice.common.domain.SecurityWrapper;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationDTOMapperWrapper mapperWrapper;
    private final ApplicationDTOMapper mapper;
    private final IApplicationCreateService applicationCreateService;
    private final IApplicationUpdateService applicationUpdateService;
    private final IApplicationReadService applicationReadService;

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority(T(pl.polsl.hrservice.user.enumerator.Role).CANDIDATE)")
    @PostMapping
    public ApplicationDTO create(@RequestBody ApplicationCreateDTO dto) {
        final var application = applicationCreateService.create(SecurityWrapper.of(mapper.map(dto)));
        return mapperWrapper.map(application);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority(T(pl.polsl.hrservice.user.enumerator.Role).CANDIDATE)")
    @PutMapping("/{id}")
    public ApplicationDTO update(@PathVariable("id") final Long id, final @RequestBody ApplicationUpdateDTO dto) {
        final var application = applicationUpdateService.update(SecurityWrapper.of(mapper.map(dto, id)));
        return mapperWrapper.map(application);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ApplicationDTO read(@PathVariable("id") final Long id) {
        final var application = applicationReadService.read(id);
        return mapperWrapper.map(application);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority(T(pl.polsl.hrservice.user.enumerator.Role).ADMIN)")
    @GetMapping
    public Page<ApplicationDTO> readAll(final ApplicationQuery query,
                                     @PageableDefault final Pageable pageable) {
        return applicationReadService.readAll(query, pageable)
                .map(mapperWrapper::map);
    }

}
