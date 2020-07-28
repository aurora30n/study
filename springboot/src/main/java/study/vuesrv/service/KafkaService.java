package study.vuesrv.service;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final static Logger log = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${msg.topic}")
    private String topic;

    @Value("${msg.groupId}")
    private String groupId;

    public void sendMsg(Object msg) {
        log.info("producer: topic={},msg={}", topic, JSON.toJSONString(msg));
        kafkaTemplate.send(topic, JSON.toJSONString(msg));
    }

    @KafkaListener(topics={"${msg.topic}"}, groupId = "${msg.groupId}")
    public void consumeMsg(ConsumerRecord<?,?> cr) {
        log.info("consumer: topic={},msg={}", cr.topic(), cr.value());
    }

}
