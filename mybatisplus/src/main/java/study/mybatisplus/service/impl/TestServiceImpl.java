package study.mybatisplus.service.impl;

import study.mybatisplus.entity.Test;
import study.mybatisplus.dao.TestDao;
import study.mybatisplus.service.TestService;
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
public class TestServiceImpl extends ServiceImpl<TestDao, Test> implements TestService {

}
