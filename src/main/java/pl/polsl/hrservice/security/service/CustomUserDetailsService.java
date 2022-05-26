package pl.polsl.hrservice.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.user.service.IUserReadService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserReadService userReadService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userReadService.readByEmail(username);
        final var grantedAuthorities = List.of(new SimpleGrantedAuthority(user.role().name()));
        return new org.springframework.security.core.userdetails.User(user.email(), user.password(),
                user.activated(),
                true,
                true,
                true,
                grantedAuthorities);
    }
}
