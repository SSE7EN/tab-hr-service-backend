package pl.polsl.hrservice.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.hrservice.common.domain.SecurityWrapper;
import pl.polsl.hrservice.user.command.UserCreateCommand;
import pl.polsl.hrservice.user.command.UserUpdateCommand;
import pl.polsl.hrservice.user.domain.User;
import pl.polsl.hrservice.user.exception.UserNotFoundException;
import pl.polsl.hrservice.user.query.UserQuery;
import pl.polsl.hrservice.user.repository.IUserRepository;

import java.time.ZonedDateTime;

/**
 * Created by piotrswierzy on 24.03.2022
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserReadService, IUserCreateService, IUserUpdateService {
    private final IUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User readByEmail(final String email) {
        return userRepository.readByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    @Transactional(readOnly = true)
    public User read(final Long id) {
        return userRepository.read(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> readAll(final UserQuery query, final Pageable page) {
        return userRepository.readAll(query, page);
    }

    @Override
    @Transactional
    public User create(final UserCreateCommand command) {
        final var user = User.builder()
                .firstName(command.firstName())
                .lastName(command.lastName())
                .email(command.email())
                .password(command.password())
                .createdOn(ZonedDateTime.now())
                .activated(true)
                .role(command.role())
                .build();

        return userRepository.create(user);
    }

    @Override
    @Transactional
    public User update(final SecurityWrapper<UserUpdateCommand> commandWrapper) {
        final var command = commandWrapper.command();
        final var user = readByEmail(commandWrapper.currentUserEmail());
        return update(
                user.toBuilder()
                        .lastName(command.lastName())
                        .firstName(command.firstName())
                        .build()
        );
    }

    @Override
    @Transactional
    public User update(final User user) {
        return userRepository.update(
                user.toBuilder()
                        .modifiedOn(ZonedDateTime.now())
                        .build()
        );
    }
}
