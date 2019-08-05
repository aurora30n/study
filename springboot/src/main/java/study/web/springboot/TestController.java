package study.web.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value="/add")
    public Object add() {
        TestEntity test = new TestEntity();
        test.setName("test" + (int)(10*Math.random()));
        testService.add(test);
        return "success";
    }

}
