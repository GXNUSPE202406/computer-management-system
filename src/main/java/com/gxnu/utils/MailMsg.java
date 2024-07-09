package com.gxnu.utils;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class MailMsg {

    @Resource
    private JavaMailSenderImpl mailSender;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean mail(String email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //生成随机验证码
        String code = CodeGeneratorUtil.generateCode(6);

        System.out.println(code);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //设置一个html邮件信息
        helper.setText("<p style='color: blue'>您的验证码为：" + code + "，有效期为一分钟。</p>", true);

        //设置邮件主题名
        helper.setSubject("学生注册验证码");

        //发送的邮箱地址
        helper.setTo(email);

        //发送人邮箱
        helper.setFrom("wangglc@foxmail.com");

        //将邮箱验证码以邮件地址为key存入redis,1分钟过期
        redisTemplate.opsForValue().set(email, code, Duration.ofMinutes(1));
        mailSender.send(mimeMessage);

        return true;
    }
}