package com.clumsycoder.odinservice.config.email;

import com.clumsycoder.controlshift.commons.email.EmailSender;
import com.clumsycoder.controlshift.commons.email.EmailService;
import com.clumsycoder.controlshift.commons.email.EmailTemplateProvider;
import com.clumsycoder.controlshift.commons.email.sendgrid.SendGridEmailSender;
import com.clumsycoder.controlshift.commons.email.sendgrid.SendGridEmailService;
import com.clumsycoder.controlshift.commons.email.sendgrid.SendGridTemplateProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(EmailProperties.class)
public class EmailConfig {

    private final EmailProperties emailProperties;

    public EmailConfig(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Bean
    public EmailSender emailSender() {
        if ("sendgrid".equalsIgnoreCase(emailProperties.getProvider())) {
            EmailProperties.SendGridConfig sg = emailProperties.getSendgrid();
            return new SendGridEmailSender(sg.getApiKey(), sg.getFromEmail());
        }

        throw new IllegalArgumentException("Unsupported email provider " + emailProperties.getProvider());
    }

    @Bean
    public EmailTemplateProvider emailTemplateProvider() {
        return new SendGridTemplateProvider();
    }

    @Bean
    public EmailService emailService(EmailSender emailSender, EmailTemplateProvider templateProvider) {
        if ("sendgrid".equalsIgnoreCase(emailProperties.getProvider())) {
            return new SendGridEmailService(emailSender, templateProvider);
        }
        throw new IllegalArgumentException("Unsupported email provider " + emailProperties.getProvider());
    }
}