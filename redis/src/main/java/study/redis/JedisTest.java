package study.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JedisTest {

    public void test() {


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

    public static void main(String[] args) {

        Device dev = null;
        Vehicle vc = null;
        List<Device> devlist = new ArrayList<Device>();
        List<Vehicle> vehlist = new ArrayList<Vehicle>();
        /*for (int i=0; i<10; i++) {
            dev = new Device("deviceId" + i, "deviceName" + i);
            vc = new Vehicle("deviceId" + i, "vin" + i, "plateNo" + i);
            devlist.add(dev);
            vehlist.add(vc);
        }
        JedisUtil.getInstance().hset("mnemonic", "device", JSON.toJSONString(devlist));// hash集合
        JedisUtil.getInstance().hset("mnemonic", "vehicle",JSON.toJSONString(vehlist));// hash集合
        */
        System.out.println("--------print Device----------");
        String res = JedisUtil.getInstance().hget("mnemonic", "device");
        devlist = JSONArray.parseArray(res, Device.class);
        for (Device dev2: devlist) {
            System.out.println(dev2.getDeviceName());
        }

        System.out.println("--------print Vehicle----------");
        res = JedisUtil.getInstance().hget("mnemonic", "vehicle");
        vehlist = JSONArray.parseArray(res, Vehicle.class);
        for (Vehicle veh2: vehlist) {
            System.out.println(veh2.getVehicleId());
        }


    }

}
