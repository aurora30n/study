package study.web.springboot;

import org.springframework.stereotype.Repository;

@Repository
public interface TestDao {

    public void insertOne(TestEntity test);

}
