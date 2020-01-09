package study.mybatisplus.dao;

import study.mybatisplus.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Light
 * @since 2020-01-09
 */
@Mapper
public interface TestDao extends BaseMapper<Test> {

}
