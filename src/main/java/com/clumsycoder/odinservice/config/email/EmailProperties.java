package com.clumsycoder.odinservice.config.email;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "email")
public class EmailProperties {
    private String provider;
    private SendGridConfig sendgrid;

    @Setter
    @Getter
    public static class SendGridConfig {
        private String apiKey;
        private String fromEmail;

    }

}