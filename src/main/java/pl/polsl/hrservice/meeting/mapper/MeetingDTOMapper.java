package pl.polsl.hrservice.meeting.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import pl.polsl.hrservice.candidate.mapper.CandidateDTOMapper;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;
import pl.polsl.hrservice.meeting.command.MeetingCreateCommand;
import pl.polsl.hrservice.meeting.command.MeetingUpdateCommand;
import pl.polsl.hrservice.meeting.domain.Meeting;
import pl.polsl.hrservice.meeting.dto.MeetingCreateDTO;
import pl.polsl.hrservice.meeting.dto.MeetingDTO;
import pl.polsl.hrservice.meeting.dto.MeetingUpdateDTO;
import pl.polsl.hrservice.user.mapper.UserDTOMapper;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Mapper(builder = @Builder(disableBuilder = true),
        uses = {
                UserDTOMapper.class,
                CandidateDTOMapper.class
        })
public interface MeetingDTOMapper {
    MeetingCreateCommand map(MeetingCreateDTO dto);
    MeetingUpdateCommand map(MeetingUpdateDTO dto, Long id);
    MeetingDTO map(Meeting domain, @Context CycleAvoidingMappingContext context);
}
