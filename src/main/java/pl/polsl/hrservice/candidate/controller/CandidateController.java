package pl.polsl.hrservice.candidate.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.hrservice.candidate.command.CandidateCreateCommand;
import pl.polsl.hrservice.candidate.dto.CandidateDTO;
import pl.polsl.hrservice.candidate.mapper.CandidateDTOMapperWrapper;
import pl.polsl.hrservice.candidate.query.CandidateQuery;
import pl.polsl.hrservice.candidate.service.ICandidateCreateService;
import pl.polsl.hrservice.candidate.service.ICandidateReadService;
import pl.polsl.hrservice.candidate.service.ICandidateUpdateService;
import pl.polsl.hrservice.common.util.SecurityUtil;
import pl.polsl.hrservice.user.dto.UserCreateDTO;
import pl.polsl.hrservice.user.dto.UserDTO;
import pl.polsl.hrservice.user.enumerator.Role;
import pl.polsl.hrservice.user.mapper.UserDTOMapper;
import pl.polsl.hrservice.user.query.UserQuery;

/**
 * Created by piotrswierzy on 16.06.2022
 */

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final UserDTOMapper userDTOMapper;
    private final CandidateDTOMapperWrapper mapperWrapper;
    private final ICandidateCreateService candidateCreateService;
    private final ICandidateReadService candidateReadService;

    @PostMapping
    public CandidateDTO create(@RequestBody UserCreateDTO dto) {
        final var candidate = candidateCreateService.create(CandidateCreateCommand.builder()
                .userCreateCommand(userDTOMapper.map(dto, Role.CANDIDATE))
                .build());
        return mapperWrapper.map(candidate);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/current")
    public CandidateDTO read() {
        return mapperWrapper.map(candidateReadService.read(SecurityUtil.getCurrentUserMainEmail()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority(T(pl.polsl.hrservice.user.enumerator.Role).ADMIN)")
    @GetMapping
    public Page<CandidateDTO> readAll(final CandidateQuery query,
                                 @PageableDefault final Pageable pageable) {
        return candidateReadService.readAll(query, pageable)
                .map(mapperWrapper::map);
    }


}
