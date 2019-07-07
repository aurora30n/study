package study.springcloud.cloudproducer;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${server.port}")
    private int port;

    @RequestMapping(value="hi")
    public String hi(String name) {
        return "Hi, " + name + ", I am from port:" + this.port;
    }

}
