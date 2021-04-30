package study;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;
import study.common.MongoUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AgStatTest
{
    @Test
    public void test() {
        HashMap<String, Integer> result = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        MongoDatabase mongoDatabase = MongoUtil.getConnectInfo();
        MongoCollection<Document> collection = mongoDatabase.getCollection("app_veh_online_info");
        List<BasicDBObject> pipeline = new ArrayList();

        BasicDBObject query = new BasicDBObject();
        query.append("date", sdf.format(new Date()));
        BasicDBObject match = new BasicDBObject("$match", query);
        BasicDBObject group = new BasicDBObject("$group", (new BasicDBObject("_id", new BasicDBObject("isp", "$isp")
                                                                                        .append("batch", "$batch"))
                                                                .append("count", new BasicDBObject("$sum", 1))));
        pipeline.add(match);
        pipeline.add(group);
        System.out.println(JSON.toJSONString(pipeline));
        AggregateIterable<Document> iterable = collection.aggregate(pipeline);
        MongoCursor<Document> its = null;
        if (iterable != null) {
            its = iterable.iterator();

            while (its.hasNext()) {
                Document doc = (Document) its.next();
                Document key = (Document) doc.get("_id");
                result.put(key.getOrDefault("isp", "").toString()+key.getOrDefault("batch", "").toString(), doc.getInteger("count"));
            }
        }
        result.forEach((k, v)->{
            System.out.println("key=" + k + "  value=" + v);
        });
    }

}
