package study.vuesrv.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.vuesrv.dao.UserDao;
import study.vuesrv.entity.User;

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
