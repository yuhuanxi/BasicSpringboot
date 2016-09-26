package im.kuka.springboot.demo.model;

import im.kuka.springboot.common.model.BaseModel;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author: shipeng.yu
 * @time: 2016年09月21日 下午9:09
 * @version: 1.0
 * @since: 1.0
 * @description: 任务Model
 */
@Data
//@Entity(name = "task")
public class Task extends BaseModel {

    /**
     * 任务标题
     */
    @Column(nullable = false)
    private String title;

    /**
     * 任务详情
     */
    @Column(nullable = false, length = 2550)
    private String detail;

    /**
     * 优先级 {@link Priority}
     */
    @Column(nullable = false)
    private String priority;

    /**
     * 任务开始时间
     */
    @Column(nullable = false)
    private Date startTime;

    /**
     * 截止日期
     */
    @Column(nullable = false)
    private Date deadLine;

    /**
     * 平台 {@link Platform}
     */
    @Column(nullable = false)
    private String platform;

    /**
     * 状态 {@link Status}
     */
    @Column(nullable = false)
    private String status;

    public Task(String title, String detail, String priority, String platform) {
        this.title = title;
        this.detail = detail;
        this.priority = priority;
        this.platform = platform;
    }

    public enum Priority {
        MAX,
        MID,
        MIN;
    }

    enum Platform {
        WEB,
        ANDROID,
        IOS;
    }

    public enum Status {
        TODO,
        DOING,
        DONE;
    }
}
