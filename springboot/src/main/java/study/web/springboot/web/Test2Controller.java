package study.web.springboot.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import study.web.springboot.comm.Res;
import study.web.springboot.service.KafkaService;

@Api(tags = "Test2")
@RestController
@RequestMapping(value = "test2", method = {RequestMethod.GET, RequestMethod.POST})
public class Test2Controller {

    private final static Logger log = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaService kafkaService;

    @ApiOperation(value = "sendMsg")
    @RequestMapping(value = "sendMsg")
    public Res sendMsg(@RequestBody String param) {
        try {
            log.info("param={}", param);
            if (param!=null) {
                kafkaService.sendMsg(param);
                return Res.success("操作成功");
            } else {
                return Res.success("参数错误");
            }
        } catch (Exception e) {
            log.error("error: ", e);
            return Res.error("操作失败");
        }

    }

}
