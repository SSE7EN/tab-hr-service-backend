package pl.polsl.hrservice.candidate.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import pl.polsl.hrservice.candidate.domain.Candidate;
import pl.polsl.hrservice.candidate.dto.CandidateDTO;
import pl.polsl.hrservice.common.mapper.CycleAvoidingMappingContext;
import pl.polsl.hrservice.user.mapper.UserDTOMapper;

/**
 * Created by piotrswierzy on 16.06.2022
 */
@Mapper(builder = @Builder(disableBuilder = true),
        uses = {
                UserDTOMapper.class
        })
public interface CandidateDTOMapper {
    CandidateDTO map(Candidate domain, @Context CycleAvoidingMappingContext context);
}
