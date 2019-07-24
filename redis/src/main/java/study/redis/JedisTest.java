package study.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class JedisTest {

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
