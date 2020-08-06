package study.vuesrv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item1 {

    private int id;
    private String name;
    private String des;
    private String photoUrl;
    private String cont;
    private Date updateTime;

}
