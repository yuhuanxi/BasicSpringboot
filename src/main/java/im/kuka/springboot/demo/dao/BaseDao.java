package im.kuka.springboot.demo.dao;

import im.kuka.springboot.demo.dao.mappers.ITaskMapper;
import im.kuka.springboot.demo.dao.mappers.IDailyCostMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: shipeng.yu
 * @time: 2016年09月22日 下午7:42
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public class BaseDao {

    @Autowired
    public ITaskMapper taskMapper;

    @Autowired
    public IDailyCostMapper dailyCostMapper;
}
