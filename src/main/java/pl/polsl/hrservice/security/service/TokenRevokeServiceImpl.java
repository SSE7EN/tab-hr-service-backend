package pl.polsl.hrservice.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.security.prop.OAuthProperties;
import pl.polsl.hrservice.user.service.IUserReadService;

/**
 * Created by piotrswierzy on 01.04.2022
 */
@Component
@RequiredArgsConstructor
public class TokenRevokeServiceImpl implements ITokenRevokeService {
    private final TokenStore tokenStore;
    private final ConsumerTokenServices tokenServices;
    private final OAuthProperties oAuthProperties;
    private final IUserReadService userReadService;

    @Override
    public void revokeTokens(Long userId) {
        final var username = userReadService.read(userId).email();
        final var tokens = tokenStore
                .findTokensByClientIdAndUserName(oAuthProperties.getClient(), username);
        tokens.forEach(token -> tokenServices.revokeToken(token.getValue()));
    }
}
