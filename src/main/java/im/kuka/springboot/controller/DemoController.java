package im.kuka.springboot.controller;

import com.google.code.ssm.Cache;
import com.google.code.ssm.api.format.SerializationType;
import im.kuka.springboot.common.util.BaseController;
import im.kuka.springboot.common.util.PagingDto;
import im.kuka.springboot.common.util.ReturnCode;
import im.kuka.springboot.demo.model.DailyCost;
import im.kuka.springboot.demo.model.Task;
import im.kuka.springboot.demo.model.User;
import im.kuka.springboot.demo.service.interfaces.IDailyCostService;
import im.kuka.springboot.demo.service.interfaces.ITaskService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: shipeng.yu
 * @time: 2016年09月21日 下午10:19
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    @Qualifier("cache")
    private Cache cache;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IDailyCostService dailyCostService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("errorMsg") String errorMsg) {
        // 默认为templates 下的视图,user.html其中html可以省略
        return new ModelAndView("/demo/index");
    }

    /**
     * 使用拦截器
     * 用户登录:在登录成功后,发送登录成功邮件
     * 登录失败,则返回登录页面,并提示错误消息
     *
     * @param httpRequest
     * @param user
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest httpRequest, User user, RedirectAttributes attributes) {
        if ("shipeng.yu".equals(user.getUsername())) {
            user.setTime(new Date());
            httpRequest.getSession().setAttribute("username", user.getUsername());
//            attributes.addAttribute("user", user); // 地址栏传值,参数会出现在url上
            attributes.addFlashAttribute("user", user); // 在页面F5刷新数据将丢失
            ModelAndView mav = new ModelAndView("redirect:/demo/success");
            return mav;
        }
        attributes.addFlashAttribute("errorMsg", "用户名错误,重新输入");
        return new ModelAndView("redirect:/demo");
    }

    // redirect 之间传值,可以用 ModelAttribute 来获取
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public ModelAndView success(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("/demo/success");
        return modelAndView;
    }

    @RequestMapping(value = "/newTask", method = RequestMethod.POST)
    public BaseAjaxResult newTask(String title, String detail, String priority, String platform) {

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

    /**
     * 单个插入
     *
     * @param category
     * @param item
     * @param money
     * @param username
     * @return
     */
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

    /**
     * 批量插入
     *
     * @param category
     * @param item
     * @param money
     * @param username
     * @return
     */
    @RequestMapping(value = "/newCosts", method = RequestMethod.POST)
    public BaseAjaxResult newDailyCosts(Integer category, String item, Float money, String username) {

        List<DailyCost> dailyCosts = new ArrayList<>();

        DailyCost dailyCost = new DailyCost();
        dailyCost.setCategory(category);
        dailyCost.setItem(item);
        dailyCost.setMoney(money);
        dailyCost.setUsername(username);
        dailyCost.setCreatedTs(new Date());

        for (int i = 0; i < 5; i++) {
            dailyCosts.add(dailyCost);
        }

        long ret = dailyCostService.saveBatch(dailyCosts);

        if (ret > 0) {
            return renderJsonSuccessed(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
        }

        return renderJsonFail();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BaseAjaxResult dailyCosts(Integer category, Integer curPage, Integer pageSize) {

        Map<String, Object> params = new HashMap<>();
        params.put("category", category);

        long count = dailyCostService.count(params);

        if (count > 0) {
            PagingDto pagingDto = new PagingDto(curPage, pageSize);
            pagingDto.setCount(count);

            params.put("offset", pagingDto.getBeginInt());
            params.put("limit", pagingDto.getPageSize());

            List<DailyCost> dailyCosts = dailyCostService.select(params);

            if (CollectionUtils.isNotEmpty(dailyCosts))
                return renderJsonAjaxPageResult(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), dailyCosts, pagingDto);
        }

        return renderJsonFail(ReturnCode.DATA_NOT_FOUND.getCode(), ReturnCode.DATA_NOT_FOUND.getMsg());
    }

    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public BaseAjaxResult dailyCost(Long id) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        DailyCost dailyCost = dailyCostService.selectOne(params);

        if (dailyCost != null) {
            return renderJsonAjaxResult(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), dailyCost);
        }

        return renderJsonFail(ReturnCode.DATA_NOT_FOUND.getCode(), ReturnCode.DATA_NOT_FOUND.getMsg());
    }

    /**
     * 修改 daily cost
     *
     * @param id
     * @param category
     * @param item
     * @param money
     * @param username
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseAjaxResult updateDailyCost(Long id, Integer category, String item, Float money, String username) {

        DailyCost dailyCost = new DailyCost();
        dailyCost.setCategory(category);
        dailyCost.setItem(item);
        dailyCost.setMoney(money);
        dailyCost.setUsername(username);
        dailyCost.setCreatedTs(new Date());
        dailyCost.setId(id);

        long ret = dailyCostService.update(dailyCost);

        if (ret > 0) {
            return renderJsonSuccessed(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
        }

        return renderJsonFail();
    }

    /**
     * 通过ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseAjaxResult deleteDailyCost(Long id) {

        long ret = dailyCostService.deleteById(id);

        if (ret > 0) {
            return renderJsonSuccessed(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
        }

        return renderJsonFail();
    }

    /**
     * 测试缓存
     * <p>
     * TODO 缓存的一些 annotation 无效,例如 @ReadThroughSingleCache
     *
     * @return
     */
    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    public BaseAjaxResult testCache() {

        String test = "";

        try {
            test = cache.get("test", SerializationType.JAVA);
            if (test != null) {
                LOG.info("from cache test:{}", test);
            } else {
                test = "Hello World";
                cache.set("test", 30, test, SerializationType.JAVA);
                LOG.info("set cache test:{}", test);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return renderJsonAjaxResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), test);
    }

}
