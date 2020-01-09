package study.mybatisplus.entity;

import study.mybatisplus.BaseData;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Light
 * @since 2020-01-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Test extends BaseData {

    private static final long serialVersionUID = 1L;

    private String name;

    private Date upateTime;


}
