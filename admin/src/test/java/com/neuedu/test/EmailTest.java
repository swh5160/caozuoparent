package com.neuedu.test;

import com.neuedu.AdminApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 施子安
 * @create
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApp.class)
public class EmailTest {

   /* @Resource
    JavaMailSender javaMailSender;*/
    @Test
    public  void handler()  {
        //邮件消息
        /*SimpleMailMessage mailMessage = new SimpleMailMessage();
        //发件邮箱
        mailMessage.setFrom("1640070547@qq.com");
        //发送给谁
        mailMessage.setTo("swh5160@163.com","2780444383@qq.com");
        //主题
        mailMessage.setSubject("邮件主题");
        //抄送
//        mailMessage.setCc("1034174907@qq.com");
        //邮件内容
        mailMessage.setText("邮件的内容：老笨猪！");*/
       /* MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.addAttachment("abc.jpg",new File("G:\\壁纸/timg11.jpg"));
        helper.setFrom("1640070547@qq.com");
        helper.setTo(new String[]{"swh5160@163.com","2780444383@qq.com"});
        helper.setSubject("邮件主题");
        helper.setText("邮件内容：壁纸");
        javaMailSender.send(mimeMessage);*/
    }
}
