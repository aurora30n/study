package study.springcloud.feignconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="${service.name}", fallback=FeignFallBack.class)
public interface FeignService {

    @RequestMapping(value="hi") //服务中方法的映射路径
    public String hi(@RequestParam String name);

}
