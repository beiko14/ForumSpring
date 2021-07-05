package com.example.demo.service;

import com.example.demo.model.NotificationEmail;
import com.example.demo.exceptions.ForumSpringException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    public void sendMail(NotificationEmail notificationEmail){
        MimeMessagePreparator messagePreparator = mimeMessage ->{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("forumspring@email.com"); //sender information
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody())); //message in html format
        };

        try{
            mailSender.send(messagePreparator);
            log.info("Activation email sent");
        } catch(MailException e){
            throw new ForumSpringException("Exception while sending the verification mail to" + notificationEmail.getRecipient());
        }

    }

}
