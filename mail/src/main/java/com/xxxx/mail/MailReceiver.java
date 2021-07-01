package com.xxxx.mail;


import com.xxxx.pojo.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailReceiver {


    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;


    @RabbitListener(queues = "mail.server")
    public void handler(Employee employee){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(employee.getEmail());
            helper.setSubject("test");
            helper.setText("123");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}