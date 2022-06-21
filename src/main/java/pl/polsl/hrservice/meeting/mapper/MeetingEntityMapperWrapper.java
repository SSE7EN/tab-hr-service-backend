package pl.polsl.hrservice.meeting.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.candidate.entity.CandidateEntity;
import pl.polsl.hrservice.candidate.mapper.CandidateEntityMapper;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;
import pl.polsl.hrservice.meeting.domain.Meeting;
import pl.polsl.hrservice.meeting.entity.MeetingEntity;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Component
@RequiredArgsConstructor
public class MeetingEntityMapperWrapper {
    private final MeetingEntityMapper mapper;

    public Meeting map(MeetingEntity entity){
        return mapper.map(entity, new CycleAvoidingMappingContext());
    }

    public MeetingEntity map(Meeting domain){
        return mapper.map(domain, new CycleAvoidingMappingContext());
    }
}
