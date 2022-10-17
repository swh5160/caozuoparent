package com.neuedu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 施子安
 * @create
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {
    @Resource
    RabbitTemplate rabbitTemplate;
    @Test
    public void handler(){
        rabbitTemplate.convertAndSend("swhemail","施子安");
    }
}
