package study.vuesrv.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import study.vuesrv.comm.ResData;
import study.vuesrv.service.KafkaService;

@Api(tags = "Kafka")
@RestController
@RequestMapping(value = "kafka", method = {RequestMethod.GET, RequestMethod.POST})
public class KafkaController {

    private final static Logger log = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaService kafkaService;

    @ApiOperation(value = "sendMsg")
    @RequestMapping(value = "sendMsg")
    public ResData sendMsg(@RequestBody String param) {
        try {
            log.info("param={}", param);
            if (param!=null) {
                kafkaService.sendMsg(param);
                return ResData.success();
            } else {
                return ResData.error("参数错误");
            }
        } catch (Exception e) {
            log.error("error: ", e);
            return ResData.error();
        }

    }

}
