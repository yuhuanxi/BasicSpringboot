package im.kuka.springboot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author: shipeng.yu
 * @time: 2016年09月27日 下午2:46
 * @version: 1.0
 * @since: 1.0
 * @description: 邮件工具类
 */
public class MailUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MailUtil.class);

    private static String[] sendTo = {"adobe1874@126.com"};

    /**
     * 发送简单邮件
     *
     * @throws Exception
     */
    public static void sendSimpleMail(JavaMailSender javaMailSender) throws Exception {
        if (javaMailSender != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("335747019@qq.com");
            message.setTo("adobe1874@126.com");
            message.setSubject("主题：简单邮件");
            message.setText("你的到来让我很高兴");
            javaMailSender.send(message);
            LOG.info("send success");
        } else {
            LOG.info("javaMailSender is null");
        }
    }

    /**
     * 发送附件
     *
     * @param javaMailSender
     * @throws Exception
     */
    public static void sendAttachmentsMail(JavaMailSender javaMailSender) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("335747019@qq.com");
        helper.setTo(sendTo);
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");

        FileSystemResource file = new FileSystemResource(new File("/Users/yuhuanxi/Program/framework/MyBatis-Spring-Boot/src/main/resources/static/img/avatar1.jpg"));
        FileSystemResource file1 = new FileSystemResource(new File("/Users/yuhuanxi/Program/framework/MyBatis-Spring-Boot/src/main/resources/static/img/avatar2.jpg"));

        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file1);

        javaMailSender.send(mimeMessage);
    }

    /**
     * 嵌入静态资源,直接展示附件内容
     *
     * @param javaMailSender
     * @throws Exception
     */
    public static void sendInlineMail(JavaMailSender javaMailSender) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("335747019@qq.com");
        helper.setTo(sendTo);
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:doubi\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(new File("/Users/yuhuanxi/Program/framework/MyBatis-Spring-Boot/src/main/resources/static/img/avatar1.jpg"));
        helper.addInline("doubi", file); // addInline函数中资源名称doubi需要与正文中cid:doubi对应起来

        javaMailSender.send(mimeMessage);

    }

    /**
     * 发送模板邮件
     *
     * @param javaMailSender
     * @throws Exception
     */
    public static void sendTemplateMail(JavaMailSender javaMailSender) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("335747019@qq.com");
        helper.setTo(sendTo);
        helper.setSubject("主题：模板邮件");

        Map<String, Object> model = new HashMap<>();
        model.put("user", "shipeng.yu");

        String html = HtmlProcessor.getHtml("mail.html", new Context(Locale.CHINA, model));

        helper.setText(html, true);

        javaMailSender.send(mimeMessage);
    }

}