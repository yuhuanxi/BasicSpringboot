package im.kuka.springboot.demo.dao.interfaces;

import im.kuka.springboot.demo.model.DailyCost;

import java.util.List;
import java.util.Map;

/**
 * @author: shipeng.yu
 * @time: 2016年09月22日 下午8:23
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public interface IDailyCostDao {

    long save(DailyCost dailyCost);

    long saveBatch(List<DailyCost> dailyCosts);

    long update(DailyCost dailyCost);

    long count(Map<String, Object> params);

    List<DailyCost> select(Map<String, Object> params);

    DailyCost selectOne(Map<String, Object> params);

    long deleteById(Long id);
}
