package im.kuka.springboot.demo.service.interfaces;

import im.kuka.springboot.demo.model.DailyCost;

/**
 * @author: shipeng.yu
 * @time: 2016年09月22日 下午7:31
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public interface IDailyCostService {

    long save(DailyCost dailyCost);
}
