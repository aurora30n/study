package study.web.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import study.web.springboot.TestEntity;

@Mapper // MyBatis 与@MapperScan配置一个就行
public interface TestDao {

    public void insertOne(TestEntity test);

}
