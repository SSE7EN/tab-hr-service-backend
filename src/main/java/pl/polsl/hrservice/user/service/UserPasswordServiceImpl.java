package pl.polsl.hrservice.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.hrservice.common.domain.SecurityWrapper;
import pl.polsl.hrservice.security.service.ITokenRevokeService;
import pl.polsl.hrservice.user.command.UserChangePasswordCommand;
import pl.polsl.hrservice.user.command.UserPasswordResetCommand;
import pl.polsl.hrservice.user.command.UserPasswordResetRequestCommand;
import pl.polsl.hrservice.user.domain.User;
import pl.polsl.hrservice.user.exception.PasswordResetTokenExpiredException;
import pl.polsl.hrservice.user.exception.UserInvalidOldPasswordException;
import pl.polsl.hrservice.user.exception.UserNotFoundException;
import pl.polsl.hrservice.user.exception.UserNotValidException;
import pl.polsl.hrservice.user.prop.TokenProperties;
import pl.polsl.hrservice.user.repository.IUserRepository;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Created by piotrswierzy on 01.04.2022
 */
@Service
@RequiredArgsConstructor
public class UserPasswordServiceImpl implements IUserPasswordService {
    private final IUserReadService userReadService;
    private final IUserUpdateService userUpdateService;
    private final IUserRepository userRepository;
    private final TokenProperties tokenProperties;
    private final ITokenRevokeService tokenRevokeService;


    @Override
    @Transactional
    public void requestPasswordReset(final UserPasswordResetRequestCommand command) {
        final var user = userReadService.readByEmail(command.email());

        if(!validateAccountState(user)){
            throw new UserNotValidException();
        }

        final var passwordResetToken = UUID.randomUUID();
        final var userWithPasswordResetToken = user.toBuilder()
                .passwordResetToken(passwordResetToken)
                .passwordResetTokenGenerationTime(ZonedDateTime.now())
                .build();


        final var updatedUser = userUpdateService.update(userWithPasswordResetToken);

    }

    @Override
    @Transactional
    public void resetPassword(final UserPasswordResetCommand command) {
        final var user = userReadService.readByEmail(command.email());


        if(!validatePasswordResetTokenExpiration(user)){
            throw new PasswordResetTokenExpiredException();
        }
        if(!validateAccountState(user)){
            throw new UserNotValidException();
        }

        userUpdateService.update(user.toBuilder()
                .passwordResetToken(null)
                .passwordResetTokenGenerationTime(null)
                .build());
        final var updatedUser =  userRepository.changePassword(user.id(), command.password())
                        .orElseThrow( () -> new UserNotFoundException(user.id()));

        tokenRevokeService.revokeTokens(user.id());
    }

    @Override
    @Transactional
    public void changePassword(final SecurityWrapper<UserChangePasswordCommand> securityWrapper) {
        final var user = userReadService.readByEmail(securityWrapper.currentUserEmail());
        final var command = securityWrapper.command();

        if(!userRepository.isPasswordValid(user.id(), command.oldPassword())) {
            throw new UserInvalidOldPasswordException();
        }

        final var updatedUser =  userRepository.changePassword(user.id(), command.newPassword())
                .orElseThrow( () -> new UserNotFoundException(user.id()));

    }

    private boolean validateAccountState(final User user) {
        return user.activated();
    }

    private boolean validatePasswordResetTokenExpiration(final User user) {
        final var generationTime = user.passwordResetTokenGenerationTime();
        final var expirationTime = generationTime
                .plusMinutes(tokenProperties.getPasswordResetTokenExpirationMinutes());
        return expirationTime.isAfter(ZonedDateTime.now());
    }
}
