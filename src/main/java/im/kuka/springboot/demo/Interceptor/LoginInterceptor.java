package im.kuka.springboot.demo.Interceptor;

import im.kuka.springboot.common.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: shipeng.yu
 * @time: 2016年09月07日 上午2:02
 * @version: 1.0
 * @since: 1.0
 * @description: 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

    public static final String[] pathPatterns = {"/demo/login"};

    JavaMailSender javaMailSender;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // TODO 获取被拦截的请求方法名,将来可能会有作用,先记下来
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LOG.info("request method:{}", handlerMethod.getMethod());

        String username = request.getParameter("username");

        if ("shipeng.yu".equals(username)) {
            LOG.info("用户验证成功,允许操作");
            return true;
        }

        // 登录验证失败,返回登录页面
        ModelAndView mav = new ModelAndView("/demo/index");
        mav.addObject("errorMsg", "用户名错误,请重新输入");
        response.sendRedirect("/demo");

        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        // 在拦截器中不能自动注入 service ,所以这里采用该方法获取
        if (javaMailSender == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            javaMailSender = (JavaMailSender) factory.getBean("javaMailSender");
            MailUtil.sendSimpleMail(javaMailSender);
            LOG.info("send mail success");
        } else {
            LOG.info("javaMailSender is null");
        }
    }

}