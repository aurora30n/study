package study.vuesrv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String name;
    private String introduce;
    private String coverImgUrl;
    private BookAuther auther;
    private BookChapter latestChapter;
}
