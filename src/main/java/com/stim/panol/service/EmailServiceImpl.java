package com.stim.panol.service;

import com.stim.panol.component.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.Future;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendEmail(EmailSender email) {
        sendEmailTools(email.getTextMessage(), email.getEmail(), email.getSubject());
    }

    @Async("taskExecutor")
    public void sendEmailTools(String textMessage, String email, String subject) {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            Thread.sleep(1000);
            helper.setTo(email);
            helper.setText(textMessage, true);
            helper.setSubject(subject);
            sender.send(message);
            LOGGER.info("Mail enviado!");
        } catch (MessagingException | InterruptedException e) {
            LOGGER.error("Hubo un error al enviar el mail: {}", e);
        }
    }

    public void upEmailCancelSolicitud(String receiver, String idSolicitud) {

        String subject = "DuocUC Pañol - Solicitud " + idSolicitud + " (Cancelada)";

        String message = "Se ha cancelado su solicitud " + idSolicitud + ". Si ha realizado una solicitud especial," +
                " vuelva a realizar otra solicitud y hable con su coordinador de carrera. " +
                "Si ha realizado una solicitud normal, probablemente se haya agotado el stock de los materiales solicitados, " +
                "puede hablar con el pañolero encargado para pedir y verificar sus materiales.";

        sendEmailTools(message, receiver, subject);
    }
}
