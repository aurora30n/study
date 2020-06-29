package study;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.Test;
import study.common.JsonUtil;
import study.common.MongoUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testUpdate() {
        DevFactory devFactory = new DevFactory();
        devFactory.setId(1);
        devFactory.setDevFactoryName("test");
        DevModel devModel = new DevModel();
        devModel.setDevModelName("test");
        devModel.setId(1);
        devModel.setDevFactory(devFactory);

        String colName = "cam-test";
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("name", "test");
        json.put("model", devModel);
        json.put("fac", devFactory);
        System.out.println(JsonUtil.toJSONString(json));

        JSONObject whereJson = new JSONObject();
        whereJson.put("id", 1);


        String jsonstr = json.toJSONString();
        String whereJsonstr = whereJson.toJSONString();

        MongoDatabase mongoDatabase = MongoUtil.getConnectInfo();;
        MongoCollection<Document> collection = mongoDatabase.getCollection(colName);
        Document document = Document.parse(jsonstr);
        document.put("createTime", System.currentTimeMillis());
        document.put("createISODate", new Date());
        Document filter = Document.parse(whereJsonstr);
        Document update = new Document();
        update.append("$set", document);
        UpdateOptions updateOptions = new UpdateOptions();
        updateOptions.upsert(true);
        System.out.println(JsonUtil.toJSONString(filter));
        System.out.println(JsonUtil.toJSONString(update));
        System.out.println(JsonUtil.toJSONString(updateOptions));
        UpdateResult res = collection.updateOne(filter, update, updateOptions);
        System.out.println("matched count = " + res.getMatchedCount());
    }

    @Test
    public void insert()
    {
        JSONObject jobj = null;
        for (int i=11; i<20; i++) {
            jobj = new JSONObject();
            jobj.put("name", "test" + i);
            MongoUtil.insert("test", JsonUtil.toJSONString(jobj));
        }
    }

    @Test
    public void update()
    {
        JSONObject jobj = null;
        JSONObject where = new JSONObject();
        where.put("name", "test19");
        jobj = new JSONObject();
        jobj.put("name", "test19u");
        MongoUtil.update("test", JsonUtil.toJSONString(where), JsonUtil.toJSONString(jobj));
    }

    @Test
    public void query()
    {
        JSONObject where = new JSONObject();
        where.put("name", "test11");
        List<Map<String, Object>> list = MongoUtil.queryList("test", JsonUtil.toJSONString(where));
        list.forEach(item->{
            System.out.println(item.get("name"));
        });
    }

    @Test
    public void delete()
    {
        JSONObject where = new JSONObject();
        where.put("name", "test11");
        long rev = MongoUtil.delete("test", JsonUtil.toJSONString(where));
        assertTrue(rev>0);
    }
}
