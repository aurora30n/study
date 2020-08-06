package study.vuesrv.web;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.vuesrv.comm.ResData;
import study.vuesrv.entity.Item1;
import study.vuesrv.entity.Item2;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "Test-Api")
@RestController
@RequestMapping("/test")
public class TestController {

    public static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @ApiOperation(value = "list")
    @RequestMapping(value = "/list")
    public ResData listItem1(@RequestBody Map<String, Object> parm) {
        try {
            String menu = parm.get("menu") == null ? null : parm.get("menu").toString();
            if (StringUtils.isBlank(menu)) {
                menu = "Menu1";
            }

            List list = Lists.newArrayList();
            switch (menu) {
                case "Menu1":
                    for (int i = 1; i <= 10; i++) {
                        Item1 test = new Item1();
                        test.setId(i);
                        test.setName("Item1-" + i);
                        test.setPhotoUrl("img/" + Item1.class.getSimpleName()+".png");
                        test.setDes("This is des");
                        test.setCont("This is content");
                        test.setUpdateTime(new Date());
                        list.add(test);
                    }
                    break;
                case "Menu2":
                    for (int i = 1; i <= 10; i++) {
                        Item2 test = new Item2();
                        test.setId(i);
                        test.setName("Item2-" + i);
                        test.setPhotoUrl("img/" + Item2.class.getSimpleName()+".png");
                        list.add(test);
                    }
                    break;
                default:
                    for (int i = 1; i <= 10; i++) {
                        Item1 test = new Item1();
                        test.setId(i);
                        test.setName("Default-" + i);
                        list.add(test);
                    }
                    break;
            }
            return ResData.success(list);
        } catch (Exception e) {
            logger.error("", e);
            return ResData.error();
        }
    }

}
