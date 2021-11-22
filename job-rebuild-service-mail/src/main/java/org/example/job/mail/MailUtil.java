package org.example.job.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * @author 85217
 */
@Component
public class MailUtil {

    @Autowired
    JavaMailSender mailSender;

    @Async(value = "asyncThreadPoolExecutor")
    public void sendMail(SimpleMailMessage message){
        System.out.println("sending。。。。"+message);
         mailSender.send(message);
    }




}
