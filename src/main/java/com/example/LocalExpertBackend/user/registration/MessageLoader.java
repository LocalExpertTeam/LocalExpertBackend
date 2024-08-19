package com.example.LocalExpertBackend.user.registration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageLoader {

    public static final String TEMPLATE_PATH = "/confirmation-message.html";
    static String messageBody;

    static {
        URL url = MessageLoader.class.getResource(TEMPLATE_PATH);
        String mainPath;
        try {
            mainPath = Paths.get(url.toURI()).toString();
            messageBody = Files.readString(Path.of(mainPath));
        } catch (URISyntaxException | NullPointerException | IOException e) {
            throw new MailTemplateNotFoundException("Mail template could not be found.", e);
        }
    }

    static String getMessageBody() {
        return messageBody;
    }
}
