package im.kuka.springboot.demo.service.impls;

import im.kuka.springboot.demo.model.DailyCost;
import im.kuka.springboot.demo.model.Task;
import im.kuka.springboot.demo.service.BaseService;
import im.kuka.springboot.demo.service.interfaces.IDailyCostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: shipeng.yu
 * @time: 2016年09月22日 下午7:31
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Service
public class DailyCostService extends BaseService implements IDailyCostService {

    @Override
    public long save(DailyCost dailyCost) {

        dailyCostDao.save(dailyCost);

        // 测试事物,当插入一条 dailyCost 后,生成一条 task,这里由于没有设置 start_time,所以数据库会报错
        // 会发现,插入成功的 dailyCost 也会回滚,表明事物已成功启用
        Task task = new Task("记账", dailyCost.getItem(), Task.Priority.MAX.name(), "WEB");
        long ret = taskDao.save(task);

        return ret;
    }

    @Override
    public long saveBatch(List<DailyCost> dailyCosts) {
        return dailyCostDao.saveBatch(dailyCosts);
    }

    @Override
    public long update(DailyCost dailyCost) {
        return dailyCostDao.update(dailyCost);
    }

    @Override
    public long count(Map<String, Object> params) {
        return dailyCostDao.count(params);
    }

    @Override
    public List<DailyCost> select(Map<String, Object> params) {
        return dailyCostDao.select(params);
    }

    @Override
    public DailyCost selectOne(Map<String, Object> params) {
        return dailyCostDao.selectOne(params);
    }

    @Override
    public long deleteById(Long id) {
        return dailyCostDao.deleteById(id);
    }
}
