package im.kuka.springboot.demo.service.impls;

import im.kuka.springboot.demo.model.Task;
import im.kuka.springboot.demo.service.BaseService;
import im.kuka.springboot.demo.service.interfaces.ITaskService;
import org.springframework.stereotype.Service;

/**
 * @author: shipeng.yu
 * @time: 2016年09月21日 下午10:15
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Service
public class TaskService extends BaseService implements ITaskService {

    @Override
    public long save(Task task) {
        return taskDao.save(task);
    }
}
