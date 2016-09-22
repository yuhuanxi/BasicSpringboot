package im.kuka.springboot.demo.dao.interfaces;

import im.kuka.springboot.demo.model.DailyCost;

/**
 * @author: shipeng.yu
 * @time: 2016年09月22日 下午8:23
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public interface IDailyCostDao {

    long save(DailyCost dailyCost);
}
