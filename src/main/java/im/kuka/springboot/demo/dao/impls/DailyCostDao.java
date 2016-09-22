package im.kuka.springboot.demo.dao.impls;

import im.kuka.springboot.demo.dao.BaseDao;
import im.kuka.springboot.demo.dao.interfaces.IDailyCostDao;
import im.kuka.springboot.demo.model.DailyCost;
import org.springframework.stereotype.Repository;

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
}
