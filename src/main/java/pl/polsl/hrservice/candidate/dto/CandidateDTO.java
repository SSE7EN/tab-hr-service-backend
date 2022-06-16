package pl.polsl.hrservice.candidate.dto;

import pl.polsl.hrservice.user.dto.UserDTO;

/**
 * Created by piotrswierzy on 16.06.2022
 */
public record CandidateDTO (
    Long id,
    UserDTO user
){

}
