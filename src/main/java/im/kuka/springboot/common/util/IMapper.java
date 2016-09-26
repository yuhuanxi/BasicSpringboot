package im.kuka.springboot.common.util;

import java.util.List;
import java.util.Map;

/**
 * @author: shipeng.yu
 * @time: 2016年09月21日 下午9:50
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public interface IMapper<O> {

    O selectOne(Map<String, Object> params);

    List<O> select(Map<String, Object> params);

    long count(Map<String, Object> params);

    int insert(O o);

    int insertBatch(List<O> os);

    int update(O o);

    int delete(Map<String, Object> params);

    int deleteById(Long id);
}
