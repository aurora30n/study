package study.web.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.web.springboot.dao.TestDao;

@Service
public class TestService {

    @Autowired
    private TestDao testDao;

    public void add(TestEntity test) throws Exception {
        testDao.insertOne(test);
        throw new Exception("dao error");
    }

}
