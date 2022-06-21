package pl.polsl.hrservice.meeting.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.hrservice.candidate.entity.CandidateEntity;
import pl.polsl.hrservice.meeting.enumerator.MeetingType;
import pl.polsl.hrservice.user.entity.UserEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Created by piotrswierzy on 31.03.2022
 */
@Entity
@Table(name = "meetings")
@Getter
@Setter
@NoArgsConstructor
public class MeetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity mainInterviewer;

    @ManyToOne
    private CandidateEntity candidate;

    @ManyToMany
    @JoinTable(name = "meeting_interviewers",
            joinColumns = { @JoinColumn(name = "fk_interviewer") },
            inverseJoinColumns = { @JoinColumn(name = "fk_meeting") })
    private Set<UserEntity> interviewers;

    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;

    private ZonedDateTime dateTime;

}
