package study.test;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @PostConstruct
    private void test() {
        List<TestVo> list = new ArrayList<>();
        for (int i=0; ; i++) {
            list.add(new TestVo(i, "title"+i));
            if (i%10000==0) {
                list.clear();
            }
        }
    }



}
