package im.kuka.springboot.demo.model;

import im.kuka.springboot.common.model.BaseModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author: shipeng.yu
 * @time: 2016年09月22日 下午7:22
 * @version: 1.0
 * @since: 1.0
 * @description: 日常支出
 */
@Data
@Entity
public class DailyCost extends BaseModel {

    @Column
    private Integer category;

    @Column
    private String item;

    @Column
    private Float money;

    @Column
    private String username;
}
