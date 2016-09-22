package im.kuka.springboot.demo.service.impls;

import im.kuka.springboot.demo.model.DailyCost;
import im.kuka.springboot.demo.service.BaseService;
import im.kuka.springboot.demo.service.interfaces.IDailyCostService;
import org.springframework.stereotype.Service;

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
        return dailyCostDao.save(dailyCost);
    }
}
