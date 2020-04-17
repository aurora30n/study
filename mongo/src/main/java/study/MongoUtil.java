package study;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MongoUtil {

    private static Properties properties;

    private static MongoDatabase mongoDatabase;


    public static MongoDatabase getConnectInfo() {
        if (properties == null) {
            properties = new Properties();
        }

        InputStream stream = null;
        try {
            stream = MongoUtil.class.getClassLoader()
                    .getResourceAsStream("mongodb.properties");
            properties.load(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取key对应的value值
        String host = properties.getProperty("mongo.host");
        int port = Integer.parseInt(properties.getProperty("mongo.port"));
        String dbname = properties.getProperty("mongo.dbname");
        String username = properties.getProperty("mongo.username");
        String password = properties.getProperty("mongo.password");
        ServerAddress serverAddress = new ServerAddress(host, port);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress);
        MongoClient mongoClient = null;
        //通过连接认证获取MongoDB连接
        if (username!=null&&password!=null&&username.trim().length()>0&&password.trim().trim().length()>0) {
            MongoCredential credential = MongoCredential.createScramSha1Credential(username, dbname, password.toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);
            mongoClient = new MongoClient(addrs, credentials);
        } else { // 无密码连接
            mongoClient = new MongoClient(addrs);
        }
        //连接到数据库
        mongoDatabase = mongoClient.getDatabase(dbname);
        return mongoDatabase;
    }

    public static PageBean getData(int pageSize, int page, Document query, String c) {
        List<Document> list = new ArrayList<>();
        if (mongoDatabase == null) {
            getConnectInfo();
        }
        final int offset = PageBean.countOffset(pageSize, page);
        final int length = pageSize;
        MongoCollection<Document> collection = mongoDatabase.getCollection(c);
        Document document = new Document("timestamp", 1);
        FindIterable<Document> findIterable = collection.find(query).sort(document);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            Document d = mongoCursor.next();
            list.add(d);
        }
        int allRow = list.size();
        int totalPage = PageBean.countTotalPage(pageSize, allRow);
        final int currentPage = PageBean.countCurrentPage(page);
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        int start = (page - 1) * pageSize;
        int end = (page * pageSize);
        if (end > allRow) {
            end = allRow;
        }
        List newList = list.subList(start, end);
        pageBean.setList(newList);
        pageBean.init();
        return pageBean;
    }

    public static void insert(String c, List<Document> documents) {
        if (mongoDatabase == null) {
            getConnectInfo();
        }
        MongoCollection<Document> collection = mongoDatabase.getCollection(c);
        collection.insertMany(documents);
        System.out.println("文档插入成功");
    }

    public static Document getOneById(Document query, String c) {
        if (mongoDatabase == null) {
            getConnectInfo();
        }
        MongoCollection<Document> collection = mongoDatabase.getCollection(c);
        FindIterable<Document> findIterable = collection.find(query);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        Document d = null;
        while (mongoCursor.hasNext()) {
            d = mongoCursor.next();
        }
        return d;
    }

    public static void update(String c, Document query, Document document) {
        if (mongoDatabase == null) {
            getConnectInfo();
        }
        MongoCollection<Document> collection = mongoDatabase.getCollection(c);
        collection.updateOne(query, document);
        System.out.println("文档更新成功");
    }
}
