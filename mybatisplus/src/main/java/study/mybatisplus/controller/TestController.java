package study.mybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import study.mybatisplus.entity.Test;
import study.mybatisplus.service.TestService;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Light
 * @since 2020-01-07
 */
@RestController
//@RequestMapping("/test")
public class TestController {
    @Resource
    private TestService testService;

    @RequestMapping(value = "/test")
    public Object test() {
        Test test = testService.getById(1);
        System.out.println(test);
        test.setUpateTime(new Date());
        testService.updateById(test);

        System.out.println("------list------");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name", "test");
        List<Test> list = testService.list(queryWrapper);
        list.forEach(item->{
            System.out.println(item.getName());
        });

        // save
        test = new Test();
        test.setName("testt0109");
        testService.save(test);
        return null;
    }

}
