package pl.polsl.hrservice.meeting.controller;

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
import pl.polsl.hrservice.application.query.ApplicationQuery;
import pl.polsl.hrservice.candidate.dto.CandidateDTO;
import pl.polsl.hrservice.common.domain.SecurityWrapper;
import pl.polsl.hrservice.common.util.SecurityUtil;
import pl.polsl.hrservice.meeting.dto.MeetingCreateDTO;
import pl.polsl.hrservice.meeting.dto.MeetingDTO;
import pl.polsl.hrservice.meeting.dto.MeetingUpdateDTO;
import pl.polsl.hrservice.meeting.mapper.MeetingDTOMapperWrapper;
import pl.polsl.hrservice.meeting.query.MeetingQuery;
import pl.polsl.hrservice.meeting.service.IMeetingCreateService;
import pl.polsl.hrservice.meeting.service.IMeetingReadService;
import pl.polsl.hrservice.meeting.service.IMeetingUpdateService;

import java.util.List;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@RestController
@RequestMapping("/meetings")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingDTOMapperWrapper mapperWrapper;
    private final IMeetingCreateService meetingCreateService;
    private final IMeetingUpdateService meetingUpdateService;
    private final IMeetingReadService meetingReadService;

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority(T(pl.polsl.hrservice.user.enumerator.Role).CANDIDATE)")
    @PostMapping
    public MeetingDTO create(@RequestBody MeetingCreateDTO dto) {
        final var application = meetingCreateService.create(mapperWrapper.map(dto));
        return mapperWrapper.map(application);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority(T(pl.polsl.hrservice.user.enumerator.Role).CANDIDATE)")
    @PutMapping("/{id}")
    public MeetingDTO update(@PathVariable("id") final Long id, final @RequestBody MeetingUpdateDTO dto) {
        final var application = meetingUpdateService.update(mapperWrapper.map(dto, id));
        return mapperWrapper.map(application);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public MeetingDTO read(@PathVariable("id") final Long id) {
        final var application = meetingReadService.read(id);
        return mapperWrapper.map(application);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/current")
    public Page<MeetingDTO> readAll(@PageableDefault final Pageable pageable) {
        return meetingReadService.readAll(MeetingQuery.builder()
                .candidateEmail(SecurityUtil.getCurrentUserMainEmail())
                .build(), pageable)
                .map(mapperWrapper::map);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority(T(pl.polsl.hrservice.user.enumerator.Role).ADMIN)")
    @GetMapping
    public Page<MeetingDTO> readAll(final MeetingQuery query,
                                    @PageableDefault final Pageable pageable) {
        return meetingReadService.readAll(query, pageable)
                .map(mapperWrapper::map);
    }

}
