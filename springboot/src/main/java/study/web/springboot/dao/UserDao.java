package study.web.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import study.web.springboot.entity.User;

@Mapper // MyBatis 与@MapperScan配置一个就行
public interface UserDao {

    public void insertOne(User test);

}
