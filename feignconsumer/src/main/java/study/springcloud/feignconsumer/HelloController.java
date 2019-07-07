package study.springcloud.feignconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    private FeignService feignService;

    @RequestMapping(value = "hi")
    public String hi(String name) {
        return this.feignService.hi(name);
    }
}