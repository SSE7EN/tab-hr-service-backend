package pl.polsl.hrservice.user.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.hrservice.common.domain.SecurityWrapper;
import pl.polsl.hrservice.common.util.SecurityUtil;
import pl.polsl.hrservice.user.Role;
import pl.polsl.hrservice.user.command.UserChangePasswordCommand;
import pl.polsl.hrservice.user.command.UserPasswordResetCommand;
import pl.polsl.hrservice.user.command.UserPasswordResetRequestCommand;
import pl.polsl.hrservice.user.dto.UserChangePasswordDTO;
import pl.polsl.hrservice.user.dto.UserCreateDTO;
import pl.polsl.hrservice.user.dto.UserDTO;
import pl.polsl.hrservice.user.dto.UserPasswordResetDTO;
import pl.polsl.hrservice.user.dto.UserPasswordResetRequestDTO;
import pl.polsl.hrservice.user.dto.UserUpdateDTO;
import pl.polsl.hrservice.user.mapper.UserDTOMapper;
import pl.polsl.hrservice.user.query.UserQuery;
import pl.polsl.hrservice.user.service.IUserCreateService;
import pl.polsl.hrservice.user.service.IUserPasswordService;
import pl.polsl.hrservice.user.service.IUserReadService;
import pl.polsl.hrservice.user.service.IUserUpdateService;

import java.util.UUID;

/**
 * Created by piotrswierzy on 25.03.2022
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDTOMapper mapper;
    private final IUserCreateService userCreateService;
    private final IUserUpdateService userUpdateService;
    private final IUserReadService userReadService;
    private final IUserPasswordService userPasswordService;

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority(T(pl.polsl.hrservice.user.enumerator).ADMIN)")
    @PostMapping("/admins")
    public UserDTO create(@RequestBody UserCreateDTO dto) {
        final var user = userCreateService.create(mapper.map(dto, Role.ADMIN));
        return mapper.map(user);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/current")
    public UserDTO update(@RequestBody UserUpdateDTO dto) {
        final var user = userUpdateService.update(SecurityWrapper.of(mapper.map(dto)));
        return mapper.map(user);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/current")
    public UserDTO read() {
        return mapper.map(userReadService.readByEmail(SecurityUtil.getCurrentUserMainEmail()));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public Page<UserDTO> readAll(final UserQuery query,
                                   @PageableDefault final Pageable pageable) {
        return userReadService.readAll(query, pageable)
                .map(mapper::map);
    }

    @PatchMapping("/password-reset/request")
    public void requestPasswordReset(@RequestBody @Validated final UserPasswordResetRequestDTO dto) {
        userPasswordService.requestPasswordReset(UserPasswordResetRequestCommand.builder()
                .email(dto.email())
                .build());
    }

    @PatchMapping("/password-reset/{email}/{token}")
    public void resetPassword(@PathVariable("email") final String email,
                              @PathVariable("token") final UUID token,
                              @RequestBody @Validated final UserPasswordResetDTO dto) {
        userPasswordService.resetPassword(UserPasswordResetCommand.builder()
                .email(email)
                .token(token)
                .password(dto.password())
                .build());
    }

    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/current/password")
    public void changePassword(@RequestBody @Validated final UserChangePasswordDTO changePasswordDto) {
        userPasswordService.changePassword(SecurityWrapper.of(
                UserChangePasswordCommand.builder()
                        .newPassword(changePasswordDto.newPassword())
                        .oldPassword(changePasswordDto.oldPassword())
                        .build())
        );
    }


}
