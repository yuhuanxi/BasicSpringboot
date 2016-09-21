package im.kuka.springboot.common.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: shipeng.yu
 * @time: 2016年09月21日 下午9:19
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@MappedSuperclass
public class BaseModel implements Serializable {
    /**
     * 主键自增长
     */
    @Id
    @GeneratedValue
    public Long id;

    /**
     * 插入时的时间戳
     */
    @Column(columnDefinition = " TIMESTAMP  NOT NULL")
    public Date createdTs;

    /**
     * 更新时的时间戳
     */
    @Column(columnDefinition = " TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    public Date updatedTs;

    public Date getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Date createdTs) {
        this.createdTs = createdTs;
    }

    public Date getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(Date updatedTs) {
        this.updatedTs = updatedTs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
