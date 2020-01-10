package study.web.springboot.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.web.springboot.entity.User;
import study.web.springboot.service.UserService;

@Api(tags = "Test")
@RestController
@RequestMapping("/test")
public class UserController {

    @Autowired
    private UserService testService;

    @ApiOperation(value = "add")
    @RequestMapping(value="/add")
    public Object add() {
        User test = new User();
        test.setName("test" + (int)(10*Math.random()));
        try {
            testService.add(test);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @ApiOperation(value = "login")
    @RequestMapping(value="/login")
    public String login() {
        User test = new User();
        test.setName("test" + (int)(10*Math.random()));
        try {
            testService.add(test);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

}
