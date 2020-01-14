package study.mybatisplus.service.impl;

import study.mybatisplus.entity.User;
import study.mybatisplus.dao.UserDao;
import study.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Light
 * @since 2020-01-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
