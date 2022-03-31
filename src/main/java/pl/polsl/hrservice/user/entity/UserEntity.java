package pl.polsl.hrservice.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.hrservice.meeting.entity.MeetingEntity;
import pl.polsl.hrservice.user.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by piotrswierzy on 31.03.2022
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private ZonedDateTime createdOn;
    private ZonedDateTime modifiedOn;
    private ZonedDateTime lastLoginOn;
    private ZonedDateTime activatedOn;

    private boolean activated;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "mainInterviewer")
    private List<MeetingEntity> leadedMeetings;

    @ManyToMany(mappedBy = "interviewers")
    private Set<MeetingEntity> meetings;
}
