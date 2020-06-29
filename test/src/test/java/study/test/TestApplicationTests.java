package study.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
class TestApplicationTests {

    @Test
    void contextLoads() {

        Collections.synchronizedMap(new HashMap<>());
        ConcurrentHashMap concurrentHashMap;
    }

}
