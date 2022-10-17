package com.neuedu.core;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.common.SwhEmail;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author 施子安
 * @create
 */
@Component
public class EmailListener {
    //取配置文件中的值
    @Value("${spring.mail.username}")
    String form;
    @Resource
    JavaMailSender javaMailSender;
    @RabbitListener(queues = "swhemail")
    public void email(Message message){
        //内容字节流
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println(body);
        SwhEmail swhEmail = JSONObject.parseObject(body,SwhEmail.class);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(form);
        mailMessage.setTo(swhEmail.getTo());
        mailMessage.setText(swhEmail.getText());
        mailMessage.setSubject(swhEmail.getSubject());
        javaMailSender.send(mailMessage);
    }
}
