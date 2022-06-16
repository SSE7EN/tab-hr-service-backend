package pl.polsl.hrservice.candidate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.candidate.entity.CandidateEntity;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Component
@RequiredArgsConstructor
public class CandidateEntityMapperWrapper {
    private final CandidateEntityMapper mapper;

    public Candidate map(CandidateEntity entity){
        return mapper.map(entity, new CycleAvoidingMappingContext());
    }

    public CandidateEntity map(Candidate domain){
        return mapper.map(domain, new CycleAvoidingMappingContext());
    }
}
