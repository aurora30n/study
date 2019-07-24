package study.redis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class JedisUtil {
    private static Jedis redis;

    public static Jedis getInstance() {
        return redis==null?new Jedis("localhost", 6379):redis;
    }

    public static void main(String[] args) {

        Vehicle vc = null;
        Device dev = null;
        for (int i=0; i<10; i++) {
            vc = new Vehicle("vehicleId" + i, "vin" + i, "plateNo" + i);
            dev = new Device("deviceId" + i, "deviceName" + i);
            JedisUtil.getInstance().zadd("typeahead:vehicle", i,JSON.toJSONString(vc));// 有序集合
            JedisUtil.getInstance().zadd("typeahead:device", i,JSON.toJSONString(dev));// 有序集合
        }


        //zrevrange
        System.out.println("---------zrevrange---------");
        Set<String> rev = JedisUtil.getInstance().zrevrange("typeahead:vehicle", 0, 2);
        for (String str: rev) {
            System.out.println(str);
        }
        JedisUtil.getInstance().zrem("typeahead:vehicle", JSON.toJSONString(vc));

        //zscan
        System.out.println("---------zscan---------");
        String cur = "0";
        ScanParams scanParams = new ScanParams();
        scanParams.count(3);
        List<Tuple> revlist = new ArrayList<Tuple>();
        ScanResult<Tuple> scanResult = null;
        do {
            scanResult = JedisUtil.getInstance().zscan("vehicle", cur, scanParams.match("*\"vin\":\"*0*\"*"));
            cur = scanResult.getStringCursor();
            System.out.println("size=" + scanResult.getResult().size());
            revlist.addAll(scanResult.getResult());
        } while(!cur.equals("0"));

        for (Tuple obj: revlist) {
            System.out.println(obj.getScore() + "-->" + obj.getElement());
        }
    }
}
