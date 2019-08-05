package study.web.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestDao testDao;

    public void add(TestEntity test) {
        testDao.insertOne(test);
    }

}
