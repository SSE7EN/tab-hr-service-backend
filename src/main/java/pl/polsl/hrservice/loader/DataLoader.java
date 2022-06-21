package pl.polsl.hrservice.loader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.application.command.ApplicationCreateCommand;
import pl.polsl.hrservice.application.service.IApplicationCreateService;
import pl.polsl.hrservice.candidate.command.CandidateCreateCommand;
import pl.polsl.hrservice.candidate.service.ICandidateCreateService;
import pl.polsl.hrservice.common.domain.SecurityWrapper;
import pl.polsl.hrservice.meeting.command.MeetingCreateCommand;
import pl.polsl.hrservice.meeting.enumerator.MeetingType;
import pl.polsl.hrservice.meeting.service.IMeetingCreateService;
import pl.polsl.hrservice.position.command.PositionCreateCommand;
import pl.polsl.hrservice.position.enumerated.ProgrammingLanguage;
import pl.polsl.hrservice.position.service.IPositionCreateService;
import pl.polsl.hrservice.user.command.UserCreateCommand;
import pl.polsl.hrservice.user.enumerator.Role;
import pl.polsl.hrservice.user.domain.User;
import pl.polsl.hrservice.common.prop.AdminProperties;
import pl.polsl.hrservice.user.repository.IUserRepository;
import pl.polsl.hrservice.user.repository.UserJpaRepository;
import pl.polsl.hrservice.user.service.IUserCreateService;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("!test")
public class DataLoader {

    private final IUserRepository userRepository;
    private final UserJpaRepository userJpaRepository;
    private final AdminProperties adminProperties;
    private final ICandidateCreateService candidateCreateService;
    private final IUserCreateService userCreateService;

    private final IPositionCreateService positionCreateService;

    private final IApplicationCreateService applicationCreateService;

    private final IMeetingCreateService meetingCreateService;

    @PostConstruct
    public void loadData() {
        if (!userJpaRepository.findAll().isEmpty()) {
            log.info("Disabling data loader because database not empty");
            return;
        }
        log.info("Running data loader");
        testData();
        createAdmin();
        log.info("Data loader finished");
    }

    private void createAdmin() {
        final var admin = User.builder()
                .role(Role.ADMIN)
                .email(adminProperties.getEmail())
                .password(adminProperties.getPassword())//TODO change later to password change flow(?)
                .activated(true)
                .build();

        userRepository.create(admin);
    }

    private void testData(){
        final var employee1 = userCreateService.create(UserCreateCommand.builder()
                .email("employee1@test.com")
                .firstName("Employee 1")
                .lastName("1")
                .password("test_password")
                .role(Role.ADMIN)
                .build());

        final var employee2 = userCreateService.create(UserCreateCommand.builder()
                .email("employee2@test.com")
                .firstName("Employee 2")
                .lastName("2")
                .password("test_password")
                .role(Role.ADMIN)
                .build());

        final var candidate1 = candidateCreateService.create(
                CandidateCreateCommand.builder()
                        .userCreateCommand(
                                UserCreateCommand.builder()
                                        .email("candidate1@test.com")
                                        .password("test_password")
                                        .firstName("Candidate 1")
                                        .lastName("1")
                                        .role(Role.CANDIDATE)
                                        .build())
                        .build());

        final var candidate2 = candidateCreateService.create(
                CandidateCreateCommand.builder()
                        .userCreateCommand(
                                UserCreateCommand.builder()
                                        .email("candidate2@test.com")
                                        .password("test_password")
                                        .firstName("Candidate 2")
                                        .lastName("2")
                                        .role(Role.CANDIDATE)
                                        .build())
                        .build());

        final var position1 = positionCreateService.create(
                PositionCreateCommand.builder()
                        .description("Position 1 description")
                        .programmingLanguages(List.of(ProgrammingLanguage.JAVA))
                        .name("Position 1")
                        .build()
        );

        final var position2 = positionCreateService.create(
                PositionCreateCommand.builder()
                        .description("Position 2 description")
                        .programmingLanguages(List.of(ProgrammingLanguage.PYTHON))
                        .name("Position 2")
                        .build()
        );

        final var position3 = positionCreateService.create(
                PositionCreateCommand.builder()
                        .description("Position 3 description")
                        .programmingLanguages(List.of(ProgrammingLanguage.C_PLUS_PLUS))
                        .name("Position 3")
                        .build()
        );

        final var application1Candidate1 = applicationCreateService.create(
                SecurityWrapper.of(ApplicationCreateCommand.builder()
                                .candidateId(candidate1.id())
                                .positionId(position1.id())
                                .description("Description")
                                .build())
                        .toBuilder()
                        .currentUserEmail(candidate1.user().email())
                        .build());

        final var application2Candidate1 = applicationCreateService.create(
                SecurityWrapper.of(ApplicationCreateCommand.builder()
                                .candidateId(candidate1.id())
                                .positionId(position2.id())
                                .description("Description")
                                .build())
                        .toBuilder()
                        .currentUserEmail(candidate1.user().email())
                        .build());

        final var application1Candidate2 = applicationCreateService.create(
                SecurityWrapper.of(ApplicationCreateCommand.builder()
                                .candidateId(candidate2.id())
                                .positionId(position1.id())
                                .description("Description")
                                .build())
                        .toBuilder()
                        .currentUserEmail(candidate2.user().email())
                        .build());

        final var meetingApplication1Position1Candidate1 = meetingCreateService.create(
                MeetingCreateCommand.builder()
                        .dateTime(ZonedDateTime.now())
                        .candidateId(candidate1.id())
                        .interviewersIds(Set.of(employee2.id()))
                        .mainInterviewerId(employee1.id())
                        .meetingType(MeetingType.HR)
                        .build());

        final var meetingApplication1Position1Candidate2 = meetingCreateService.create(
                MeetingCreateCommand.builder()
                        .dateTime(ZonedDateTime.now())
                        .candidateId(candidate2.id())
                        .interviewersIds(Set.of(employee2.id()))
                        .mainInterviewerId(employee1.id())
                        .meetingType(MeetingType.FINAL)
                        .build());



    }



}
