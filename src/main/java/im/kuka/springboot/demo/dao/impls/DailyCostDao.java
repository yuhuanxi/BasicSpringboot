package im.kuka.springboot.demo.dao.impls;

import im.kuka.springboot.demo.dao.BaseDao;
import im.kuka.springboot.demo.dao.interfaces.IDailyCostDao;
import im.kuka.springboot.demo.model.DailyCost;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: shipeng.yu
 * @time: 2016年09月22日 下午8:23
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Repository
public class DailyCostDao extends BaseDao implements IDailyCostDao {

    @Override
    public long save(DailyCost dailyCost) {
        return dailyCostMapper.insert(dailyCost);
    }

    @Override
    public long saveBatch(List<DailyCost> dailyCosts) {
        return dailyCostMapper.insertBatch(dailyCosts);
    }

    @Override
    public long update(DailyCost dailyCost) {
        return dailyCostMapper.update(dailyCost);
    }

    @Override
    public long count(Map<String, Object> params) {
        return dailyCostMapper.count(params);
    }

    @Override
    public List<DailyCost> select(Map<String, Object> params) {
        return dailyCostMapper.select(params);
    }

    @Override
    public DailyCost selectOne(Map<String, Object> params) {
        return dailyCostMapper.selectOne(params);
    }

    @Override
    public long deleteById(Long id) {
        return dailyCostMapper.deleteById(id);
    }
}
