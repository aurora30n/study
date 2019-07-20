package study.redis;

import com.alibaba.fastjson.JSON;

public class JedisTest {

    public static void main(String[] args) {

        Device dev = null;
        Vehicle vc = null;
        for (int i=0; i<10; i++) {
            dev = new Device("deviceId" + i, "deviceName" + i);
            vc = new Vehicle("deviceId" + i, "vin" + i, "plateNo" + i);
            JedisUtil.getInstance().hset("mnemonic", "device", JSON.toJSONString(dev));// hash集合
            JedisUtil.getInstance().hset("mnemonic", "vehicle",JSON.toJSONString(vc));// hash集合
        }
    }

}
