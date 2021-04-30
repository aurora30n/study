package study.vuesrv.web;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.vuesrv.comm.ResData;
import study.vuesrv.entity.BookAuther;
import study.vuesrv.entity.BookChapter;
import study.vuesrv.entity.BookDTO;
import study.vuesrv.entity.Page;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "BookTest-Api")
@RestController
public class BookTestCtrl {

    public static final Logger logger = LoggerFactory.getLogger(BookTestCtrl.class);

    private static List<BookAuther> autherList = Lists.newArrayList();
    private static int autherCount = 0;
    private static Map<String, Long> chapterNum = new HashMap<>();


    static {
        for (long i=1; i<=2; i++) {
            BookAuther auther = new BookAuther(i, "auther-"+i, "");
            autherList.add(auther);
            autherCount++;
        }
    }

    @ApiOperation(value = "bookShelf/list", httpMethod = "POST")
    @RequestMapping(value = "bookShelf/list")
    public ResData bookShelfList() {
        try {
            Thread.sleep(5000);
            List list = Lists.newArrayList();
            for (long i = 1; i <= 3; i++) {
                BookDTO obj = new BookDTO();
                obj.setId(i);
                obj.setName("book" + i);
                obj.setCoverImgUrl("/img/Item1.png");
                obj.setIntroduce("This is "+obj.getName());
                obj.setAuther(autherList.get((int)i%autherCount));
                if (chapterNum.get(obj.getName()+"-chapter")==null) {
                    obj.setLatestChapter(new BookChapter(chapterNum.get(obj.getName()+"-chapter"),
                            obj.getName()+"-chapter" + chapterNum.getOrDefault(obj.getName()+"-chapter", 1L), null, new Date(), i));
                }
                list.add(obj);
            }
            return ResData.success(list);
        } catch (Exception e) {
            logger.error("", e);
            return ResData.error();
        }
    }

    @ApiOperation(value = "book/list", httpMethod = "POST")
    @RequestMapping(value = "book/list")
    public ResData bookList(@RequestBody Page page) {
        try {

            List list = Lists.newArrayList();
            for (long i = page.getPageIndex()*page.getPageSize(); i < page.getPageIndex()*page.getPageSize()+page.getPageSize(); i++) {
                BookDTO obj = new BookDTO();
                obj.setId(i);
                obj.setName("book" + i);
                obj.setCoverImgUrl("/img/Item1.png");
                obj.setIntroduce("This is "+obj.getName());
                obj.setAuther(autherList.get((int)i%autherCount));
                list.add(obj);
            }
            return ResData.success(list);
        } catch (Exception e) {
            logger.error("", e);
            return ResData.error();
        }
    }

    @ApiOperation(value = "book/findById", httpMethod = "POST")
    @RequestMapping(value = "book/findById")
    public ResData bookFindById(@RequestBody Map<String, Object> parm) {
        try {

            Long bookId = parm.get("bookId") == null ? null : Long.parseLong(parm.get("bookId").toString());
            if (bookId==null) {
                return ResData.error("bookId不能为空");
            }

            BookDTO obj = new BookDTO();
            obj.setId(bookId);
            obj.setName("book" + bookId);
            obj.setCoverImgUrl("/img/Item1.png");
            obj.setIntroduce("This is "+obj.getName());
            obj.setAuther(autherList.get((int)(bookId%autherCount)));
            return ResData.success(obj);
        } catch (Exception e) {
            logger.error("", e);
            return ResData.error();
        }
    }

    @ApiOperation(value = "bookChapter/list", httpMethod = "POST")
    @RequestMapping(value = "bookChapter/list")
    public ResData bookChapterList(@RequestBody Map<String, Object> parm) {
        try {
            Long chapterId = parm.get("chapterId") == null ? 0 : Long.parseLong(parm.get("chapterId").toString());
            Long bookId = parm.get("bookId") == null ? null : Long.parseLong(parm.get("bookId").toString());
            if (bookId==null) {
                return ResData.error("bookId不能为空");
            }

            List list = Lists.newArrayList();
            for (long i = chapterId; i <= chapterId+2; i++) {
                BookChapter obj = new BookChapter();
                obj.setId(i);
                obj.setName("book" + bookId +"-chapter" + i);
                obj.setUpdateTime(new Date());
                list.add(obj);
            }
            return ResData.success(list);
        } catch (Exception e) {
            logger.error("", e);
            return ResData.error();
        }
    }

    @ApiOperation(value = "bookChapter/findById", httpMethod = "POST")
    @RequestMapping(value = "bookChapter/findById")
    public ResData bookChapterFindById(@RequestBody Map<String, Object> parm) {
        try {
            Long chapterId = parm.get("chapterId") == null ? null : Long.parseLong(parm.get("chapterId").toString());
            if (chapterId==null) {
                return ResData.error("chapterId不能为空");
            }

            BookChapter obj = new BookChapter();
            obj.setId(chapterId);
            obj.setName("chapter" + chapterId);
            obj.setUpdateTime(new Date());
            obj.setCont(getCont("chapter" + chapterId + "-content"));
            return ResData.success(obj);
        } catch (Exception e) {
            logger.error("", e);
            return ResData.error();
        }
    }

    private String getCont(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<30; i++) {
            sb.append(str);
            sb.append(i);
            sb.append("<br/>");
        }
        return sb.toString();
    }
}
