package org.example.service.interfaces;

import java.io.File;

public interface IEmailService {

    void sendEmail(String[] toUser, String subject, String text);

    void sendEmailWithFile(String[] toUser, String subject, String text, File file);

}