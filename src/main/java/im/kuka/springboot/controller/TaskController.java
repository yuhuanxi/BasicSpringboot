package im.kuka.springboot.controller;

import im.kuka.springboot.common.util.BaseController;
import im.kuka.springboot.common.util.ReturnCode;
import im.kuka.springboot.demo.model.DailyCost;
import im.kuka.springboot.demo.model.Task;
import im.kuka.springboot.demo.service.interfaces.IDailyCostService;
import im.kuka.springboot.demo.service.interfaces.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author: shipeng.yu
 * @time: 2016年09月21日 下午10:19
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IDailyCostService dailyCostService;

    @RequestMapping(value = "/newTask", method = RequestMethod.POST)
    public BaseAjaxResult newTask(String title, String detail, Integer priority, String platform) {

        Task task = new Task(title, detail, priority, platform);
        task.setDeadLine(new Date());
        task.setStartTime(new Date());
        task.setStatus(Task.Status.TODO.name());

        long ret = taskService.save(task);

        if (ret > 0) {
            return renderJsonSuccessed(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
        }

        return renderJsonFail();
    }

    @RequestMapping(value = "/newCost", method = RequestMethod.POST)
    public BaseAjaxResult newDailyCost(Integer category, String item, Float money, String username) {

        DailyCost dailyCost = new DailyCost();
        dailyCost.setCategory(category);
        dailyCost.setItem(item);
        dailyCost.setMoney(money);
        dailyCost.setUsername(username);
        dailyCost.setCreatedTs(new Date());

        long ret = dailyCostService.save(dailyCost);

        if (ret > 0) {
            return renderJsonSuccessed(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
        }

        return renderJsonFail();
    }

}
