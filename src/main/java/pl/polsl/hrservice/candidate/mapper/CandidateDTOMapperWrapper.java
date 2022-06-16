package pl.polsl.hrservice.candidate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.candidate.dto.CandidateDTO;
import pl.polsl.hrservice.candidate.entity.CandidateEntity;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Component
@RequiredArgsConstructor
public class CandidateDTOMapperWrapper {
    private final CandidateDTOMapper mapper;

    public CandidateDTO map(Candidate domain){
        return mapper.map(domain, new CycleAvoidingMappingContext());
    }
}
