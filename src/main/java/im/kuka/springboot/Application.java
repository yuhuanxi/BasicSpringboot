package im.kuka.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: shipeng.yu
 * @time: 2016年09月14日 下午1:11
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
//@EnableWebMvc 增加该注解以后WebMvcAutoConfiguration中配置就不会生效，你需要自己来配置需要的每一项
@SpringBootApplication
@EnableScheduling
// 去掉 dataSource 自动注入,此时如果没有dataSource bean 的话,启用该自动注解会报错
//@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
