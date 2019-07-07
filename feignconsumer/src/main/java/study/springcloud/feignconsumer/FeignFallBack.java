package study.springcloud.feignconsumer;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class FeignFallBack implements FeignService {
    @Override
    public String hi(@RequestParam String name) {
        return "Error!";
    }
}
