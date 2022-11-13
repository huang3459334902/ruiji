package com.huang.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class MailUtils {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String mail, HttpSession session) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("登录验证");

        String code = ValidateCodeUtils.generateValidateCode(4).toString();

        session.setAttribute("mail",mail);
        session.setAttribute("code",code);

        mailMessage.setText("验证码:"+code);
//        mailMessage.setText("傻逼!!!");
        mailMessage.setTo(mail);

        mailMessage.setFrom(from);
        System.out.println(mailMessage);
        javaMailSender.send(mailMessage);

        log.info("对账号"+mail+"发送成功");
    }

}
