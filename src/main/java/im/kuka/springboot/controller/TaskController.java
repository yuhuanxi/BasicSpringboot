package im.kuka.springboot.controller;

import im.kuka.springboot.demo.model.Task;
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
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void newTask(String title, String detail, Integer priority, String platform) {

        Task task = new Task(title, detail, priority, platform);
        task.setDeadLine(new Date());
        task.setStartTime(new Date());
        task.setStatus(Task.Status.TODO.name());

        long ret = taskService.save(task);

        System.out.println(ret);
    }
}
