package com.neuedu.aop;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.common.SwhEmail;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 施子安
 * @create
 */
@Component
@Aspect
public class AdminAop {

    @Resource
    RabbitTemplate rabbitTemplate;
    //..忽略，添加完之后，并且成功调用此方法
    @AfterReturning(value = "execution(public * com.neuedu.service.impl.UmsUserServiceImpl.add(..))")
    public void sendMail(JoinPoint joinPoint){
        //获取参数
        Object[] args = joinPoint.getArgs();
        String name = args[0].toString();
        String phone = args[1].toString();
        String email = args[2].toString();
        String password = args[4].toString();

        StringBuilder text = new StringBuilder();
        text.append("尊敬的").append(name).append(":\n")
                .append("\t系统已为您创建了使用账号,登录名:")
                .append(phone).append("密码:")
                .append(password);
        SwhEmail swhEmail = new SwhEmail(text.toString(),email,"系统消息");
        rabbitTemplate.convertAndSend("swhemail", JSONObject.toJSONString(swhEmail));
    }
}
