package study;

import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        List<Document> docs = new ArrayList<>();
        Document doc = null;
        for (int i=11; i<20; i++) {
            doc = new Document();
            doc.put("name", "test" + i);
            docs.add(doc);
        }
        MongoUtil.insert("test", docs);
    }
}
