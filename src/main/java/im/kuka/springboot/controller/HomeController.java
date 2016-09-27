package im.kuka.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Cliff
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"", "/home"}, method = RequestMethod.GET)
    public String home(HttpServletRequest httpRequest) {
        return "chat/index";
    }

}
