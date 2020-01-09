package study.mybatisplus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BaseData {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
}
