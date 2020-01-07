package study.mybatisplus.dao;

import org.apache.ibatis.annotations.Mapper;
import study.mybatisplus.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Light
 * @since 2020-01-07
 */
@Mapper
public interface TestDao extends BaseMapper<Test> {

}
