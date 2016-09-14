package im.kuka.springboot.controller;

import im.kuka.springboot.demo.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: shipeng.yu
 * @time: 2016年09月14日 下午1:01
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@RestController
public class HelloWorldController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Hello World";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User greeting(@RequestParam(value = "name", defaultValue = "shipeng.yu") String name, String address) {
        return new User(name, address);
    }
}
