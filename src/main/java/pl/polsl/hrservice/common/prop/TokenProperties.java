package pl.polsl.hrservice.common.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by piotrswierzy on 01.04.2022
 */
@ConfigurationProperties(prefix = "token")
@PropertySource("classpath:application.properties")
@Getter
@Setter
public class TokenProperties {
    private Long passwordResetTokenExpirationMinutes;
}
