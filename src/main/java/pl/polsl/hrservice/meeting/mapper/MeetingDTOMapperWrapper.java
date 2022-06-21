package pl.polsl.hrservice.meeting.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;
import pl.polsl.hrservice.meeting.command.MeetingCreateCommand;
import pl.polsl.hrservice.meeting.command.MeetingUpdateCommand;
import pl.polsl.hrservice.meeting.domain.Meeting;
import pl.polsl.hrservice.meeting.dto.MeetingCreateDTO;
import pl.polsl.hrservice.meeting.dto.MeetingDTO;
import pl.polsl.hrservice.meeting.dto.MeetingUpdateDTO;
import pl.polsl.hrservice.meeting.entity.MeetingEntity;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Component
@RequiredArgsConstructor
public class MeetingDTOMapperWrapper {
    private final MeetingDTOMapper mapper;

    public MeetingDTO map(Meeting domain){
        return mapper.map(domain, new CycleAvoidingMappingContext());
    }

    public MeetingCreateCommand map(MeetingCreateDTO dto){
        return mapper.map(dto);
    }

    public MeetingUpdateCommand map(MeetingUpdateDTO dto, Long id){
        return mapper.map(dto, id);
    }
}
