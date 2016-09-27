package im.kuka.springboot.demo.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // TODO 获取被拦截的请求方法名,将来可能会有作用,先记下来
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LOG.info("request method:{}", handlerMethod.getMethod());

        String username = (String) request.getSession().getAttribute("username");

        if ("shipeng.yu".equals(username)) {
            LOG.info("用户验证成功,允许操作");
            return true;
        }

        // 登录验证失败,返回登录页面
        LOG.info("用户验证失败,请重新登录");
        response.sendRedirect("/demo");
        return false;
    }

}