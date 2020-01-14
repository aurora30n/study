package study.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import study.mybatisplus.entity.User;
import study.mybatisplus.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ServiceTest {

    private static final String nameSuffix = "名称";
    private static IdGen idGen = new IdGen(1, 1);

    @Resource
    private UserService userService;

    @Test
    void add() {
        User u = new User();
        u.setAccount("a");
        u.setId(idGen.nextId());
        u.setName(u.getAccount() + nameSuffix);
        u.setAge(new Long(u.getId()%100).intValue());
        u.setUpateTime(new Date());
        userService.save(u);

        List<User> users = new ArrayList<>();
        for (int i=0; i<3; i++) {
            u = new User();
            u.setAccount("a" + i);
            u.setId(idGen.nextId());
            u.setName(u.getAccount() + nameSuffix);
            u.setAge(new Long(u.getId()%100).intValue());
            u.setUpateTime(new Date());
            users.add(u);
        }
        userService.saveBatch(users, 2);
    }

    @Test
    void saveOrUpdate() {
        User u = new User();
        u.setAccount("a");
        u.setId(4967763525373952L);
        u.setName(u.getAccount() + "-saveOrUpdate-2");
        u.setAge(new Long(u.getId()%100).intValue());
        u.setUpateTime(new Date());

        userService.saveOrUpdate(u);
    }

    @Test
    void remove() {
        userService.remove(new QueryWrapper<>());
    }
}
