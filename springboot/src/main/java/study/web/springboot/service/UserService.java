package study.web.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.web.springboot.entity.User;
import study.web.springboot.dao.UserDao;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserDao testDao;

    @Transactional
    public void add(User test) throws Exception {
        testDao.insertOne(test);
    }

}
