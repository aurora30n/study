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
public class User extends BaseData {

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(String account, String name, Integer age) {
        this.account = account;
        this.name = name;
        this.age = age;
        this.upateTime = new Date();
    }

    private String account;
    private String name;
    private Integer age;
    private Date upateTime;


}
