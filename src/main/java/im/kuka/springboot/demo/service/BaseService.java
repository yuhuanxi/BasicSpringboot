package im.kuka.springboot.demo.service;

import im.kuka.springboot.demo.dao.interfaces.IDailyCostDao;
import im.kuka.springboot.demo.dao.interfaces.ITaskDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: shipeng.yu
 * @time: 2016年09月22日 下午7:36
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public class BaseService {

    @Autowired
    protected ITaskDao taskDao;

    @Autowired
    protected IDailyCostDao dailyCostDao;
}
