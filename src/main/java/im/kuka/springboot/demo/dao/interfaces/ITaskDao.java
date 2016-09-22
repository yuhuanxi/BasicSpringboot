package im.kuka.springboot.demo.dao.interfaces;

import im.kuka.springboot.demo.model.Task;

/**
 * @author: shipeng.yu
 * @time: 2016年09月21日 下午10:14
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public interface ITaskDao {

    long save(Task task);
}
