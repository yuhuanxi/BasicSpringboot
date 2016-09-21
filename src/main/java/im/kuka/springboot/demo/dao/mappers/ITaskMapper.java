package im.kuka.springboot.demo.dao.mappers;

import im.kuka.springboot.common.config.ProdRepository;
import im.kuka.springboot.common.util.IMapper;
import im.kuka.springboot.demo.model.Task;

/**
 * @author: shipeng.yu
 * @time: 2016年09月21日 下午9:58
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@ProdRepository // 默认不注解即为开发模式
public interface ITaskMapper extends IMapper<Task> {
}
