package study.springcloud.cloudconsumer;

import com.netflix.discovery.converters.Auto;
import org.bouncycastle.util.encoders.UrlBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Value("${service.prefixUrl}")
    private  String servicePrefixUrl;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value="hi")
    public String hi(String name) {
        return this.restTemplate.getForObject(servicePrefixUrl + "hi?name=" + name, String.class );
    }

}
