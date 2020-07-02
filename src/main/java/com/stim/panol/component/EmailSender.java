package com.stim.panol.component;

import com.stim.panol.service.EmailServiceImpl;

public class EmailSender {

    private String textMessage;
    private String email;
    private String subject;

    public EmailSender() {
    }

    public EmailSender(String textMessage, String email, String subject) {
        this.textMessage = textMessage;
        this.email = email;
        this.subject = subject;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
