package study.web.springboot.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.web.springboot.TestEntity;
import study.web.springboot.TestService;

@Api(tags = "Test")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value = "add")
    @RequestMapping(value="/add")
    public Object add() {
        TestEntity test = new TestEntity();
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
