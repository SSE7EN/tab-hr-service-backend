package pl.polsl.hrservice.meeting.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.polsl.hrservice.meeting.domain.Meeting;
import pl.polsl.hrservice.meeting.entity.MeetingEntity;
import pl.polsl.hrservice.meeting.mapper.MeetingEntityMapperWrapper;
import pl.polsl.hrservice.meeting.query.MeetingQuery;

import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.*;
import static pl.polsl.hrservice.meeting.repository.MeetingSpecification.candidateEmail;

/**
 * Created by piotrswierzy on 21.06.2022
 */
@Component
@RequiredArgsConstructor
public class MeetingRepositoryImpl implements IMeetingRepository {
    private final MeetingJpaRepository meetingJpaRepository;
    private final MeetingEntityMapperWrapper mapperWrapper;

    @Override
    public Meeting create(final Meeting meeting) {
        return mapperWrapper.map(meetingJpaRepository.save(mapperWrapper.map(meeting)));
    }

    @Override
    public Optional<Meeting> read(final Long id) {
        return meetingJpaRepository.findById(id)
                .map(mapperWrapper::map);
    }

    @Override
    public Page<Meeting> readAll(final MeetingQuery query,final Pageable pageable) {
        return meetingJpaRepository.findAll(queryToSpec(query), pageable)
                .map(mapperWrapper::map);
    }

    private Specification<MeetingEntity> queryToSpec(final MeetingQuery query){
        return where(candidateEmail(query.candidateEmail()));
    }
}
