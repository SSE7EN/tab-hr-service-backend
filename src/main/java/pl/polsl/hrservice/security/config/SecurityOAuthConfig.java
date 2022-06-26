package pl.polsl.hrservice.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import pl.polsl.hrservice.security.prop.OAuthProperties;
import pl.polsl.hrservice.security.service.CustomUserDetailsService;

import java.util.List;

public class SecurityOAuthConfig {

    @Configuration
    @EnableAuthorizationServer
    @RequiredArgsConstructor
    public static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

        @Qualifier("authenticationManagerBean")
        private final AuthenticationManager authenticationManager;
        private final CustomUserDetailsService customUserDetailsService;
        private final OAuthProperties oAuthProperties;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints.authenticationManager(authenticationManager);
            endpoints.tokenGranter(tokenGranter(endpoints));
            endpoints.tokenStore(tokenStore());
            endpoints.userDetailsService(customUserDetailsService);
            endpoints.tokenServices(tokenServices());
        }

        private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
            final var granters = List.of(endpoints.getTokenGranter());
            return new CompositeTokenGranter(granters);
        }

        @Bean
        public TokenStore tokenStore() {
            return new InMemoryTokenStore();
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            defaultTokenServices.setTokenStore(tokenStore());
            defaultTokenServices.setSupportRefreshToken(true);
            defaultTokenServices.setReuseRefreshToken(false);
            return defaultTokenServices;
        }

        /**
         * SuppressWarnings annotation is used in order to pass sonar error "Credentials should not be hard-coded".
         * In this particular method "password" is a grant type, not some hardcoded secret
         */
        @Override
        @SuppressWarnings("squid:S2068")
        public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient(oAuthProperties.getClient())
                    .authorizedGrantTypes("password", "refresh_token")
                    .authorities("ROLE_API")
                    .scopes("read", "write")
                    .accessTokenValiditySeconds(60 * 60 * 8)
                    .secret(oAuthProperties.getSecret())
                    .autoApprove(true);
        }
    }

    @Configuration
    @EnableResourceServer
    @RequiredArgsConstructor
    public static class ResourceServerConfig extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(final HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .cors().disable()
                    .authorizeRequests()
                    .antMatchers("/v3/api-docs/**", "/configuration/ui", "/swagger-resources/**",
                            "/configuration/security", "/swagger-ui/**", "/webjars/**", "/auth/**",
                            "/swagger-ui.html",
                            "/actuator/health",
                            "/images/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/candidates").permitAll()
                    .antMatchers(HttpMethod.GET, "/positions", "/positions/{id}").permitAll()
                    .antMatchers(HttpMethod.POST, "/documents").permitAll()
                    .antMatchers(HttpMethod.PUT, "/documents/{id}").permitAll()
                    .anyRequest().authenticated();
        }
    }

    @Configuration
    public static class Encoders {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}
