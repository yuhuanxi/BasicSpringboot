package im.kuka.springboot.demo.dao.impls;

import im.kuka.springboot.demo.dao.BaseDao;
import im.kuka.springboot.demo.dao.interfaces.ITaskDao;
import im.kuka.springboot.demo.model.Task;
import org.springframework.stereotype.Repository;

/**
 * @author: shipeng.yu
 * @time: 2016年09月22日 下午7:42
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Repository
public class TaskDao extends BaseDao implements ITaskDao {

    @Override
    public long save(Task task) {
        return taskMapper.insert(task);
    }
}
