package im.kuka.springboot.demo.model;

import lombok.Data;

/**
 * @author: shipeng.yu
 * @time: 2016年09月14日 下午1:15
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Data
public class User {

    private String name;

    private String address;

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
