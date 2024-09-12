package com.local_expert.backend.account_management.registration;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class AccountConfirmationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final MessageSource messages;
    private final JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(@NonNull OnRegistrationCompleteEvent event) {
        try {
            sendConfirmRegistrationEmail(event);
        } catch (MessagingException e) {
            log.error("Confirm registration email has not been sent.");
        }
    }

    private void sendConfirmRegistrationEmail(OnRegistrationCompleteEvent event) throws MessagingException {
        UserDAO user = event.getUser();

        mailSender.send(getMimeMessage(event, user, event.getVerificationCode()));
    }

    private MimeMessage getMimeMessage(OnRegistrationCompleteEvent event,
                                       UserDAO user,
                                       String activationCode) throws MessagingException {
        String recipientAddress = user.getMail();
        String subject = "Registration Confirmation";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(getEmailContent(activationCode, event), true);
        helper.setTo(recipientAddress);
        helper.setSubject(subject);
        return mimeMessage;
    }

    private String getEmailContent(String activationCode, OnRegistrationCompleteEvent event) {
        String headerText = messages.getMessage("confirmation-email-header-text", null, event.getLocale());
        String instructionText = messages.getMessage("confirmation-email-instruction-text", null, event.getLocale());
        String endText = messages.getMessage("confirmation-email-end-text", null, event.getLocale());

        String messageBody = MessageLoader.getMessageBody();
        return messageBody.replace("XXXX", activationCode)
                            .replace("header-text", headerText)
                            .replace("instruction-text", instructionText)
                            .replace("end-text", endText);
    }
}
