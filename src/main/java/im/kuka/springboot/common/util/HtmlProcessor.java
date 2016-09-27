package im.kuka.springboot.common.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * @author: shipeng.yu
 * @time: 2016年09月27日 下午6:10
 * @version: 1.0
 * @since: 1.0
 * @description: html 处理器,获取模板中的文本
 */
public class HtmlProcessor {

    private static TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(getTemplateResolver());
        return templateEngine;
    }

    private static TemplateResolver getTemplateResolver() {
        TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setOrder(1);
        return templateResolver;
    }

    public static String getHtml(String fileName, Context ctx) {
        return templateEngine().process(fileName, ctx);
    }
}
