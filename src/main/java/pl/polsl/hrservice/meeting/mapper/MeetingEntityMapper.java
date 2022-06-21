package pl.polsl.hrservice.meeting.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.candidate.entity.CandidateEntity;
import pl.polsl.hrservice.candidate.mapper.CandidateEntityMapper;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;
import pl.polsl.hrservice.meeting.domain.Meeting;
import pl.polsl.hrservice.meeting.entity.MeetingEntity;
import pl.polsl.hrservice.user.mapper.UserEntityMapper;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Mapper(builder = @Builder(disableBuilder = true),
        uses = {
                UserEntityMapper.class,
                CandidateEntityMapper.class
        })
public interface MeetingEntityMapper {
    Meeting map(MeetingEntity entity, @Context CycleAvoidingMappingContext context);
    MeetingEntity map(Meeting domain, @Context CycleAvoidingMappingContext context);
}
