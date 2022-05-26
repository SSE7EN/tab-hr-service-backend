package pl.polsl.hrservice.security.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "oauth")
@Getter
@Setter
public class OAuthProperties {

    private String client;
    private String secret;
}
