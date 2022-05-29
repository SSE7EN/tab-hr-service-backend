package pl.polsl.hrservice.loader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.user.Role;
import pl.polsl.hrservice.user.domain.User;
import pl.polsl.hrservice.user.prop.AdminProperties;
import pl.polsl.hrservice.user.repository.IUserRepository;
import pl.polsl.hrservice.user.repository.UserJpaRepository;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("!test")
public class DataLoader {

    private final IUserRepository userRepository;
    private final UserJpaRepository userJpaRepository;
    private final AdminProperties adminProperties;

    @PostConstruct
    public void loadData() {
        if (!userJpaRepository.findAll().isEmpty()) {
            log.info("Disabling data loader because database not empty");
            return;
        }
        log.info("Running data loader");
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

}
