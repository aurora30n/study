package study.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import redis.clients.jedis.*;

import java.util.*;

public class JedisUtil {
    private static JedisCluster jedisCluster;

    static {
        ResourceBundle redisRb = ResourceBundle.getBundle("application");
        String addr = redisRb.getString("redis.addr");
        int total = Integer.parseInt(redisRb.getString("redis.total"));
        int idle = Integer.parseInt(redisRb.getString("redis.idle"));
        int maxWait = Integer.parseInt(redisRb.getString("redis.maxWait"));
        String password = null; //redisRb.getString("reids.password");
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(total);
        config.setMaxIdle(idle);
        config.setMaxWaitMillis(maxWait);
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        for (String host : addr.split(",")) {
            hostAndPorts.add(new HostAndPort(host.split(":")[0], Integer.parseInt(host.split(":")[1])));
        }
        if (password!=null && !"".equals(password.trim())) {
            jedisCluster = new JedisCluster(hostAndPorts, 2000, 2000, 5, config);
        } else {
            jedisCluster = new JedisCluster(hostAndPorts, 2000, 2000, 5, password, config);
        }
    }

    public static JedisCluster getInstance() {
        return jedisCluster;
    }

    public static void main(String[] args) {
        System.out.println(JedisUtil.getInstance().hget("vehicle_base_info", "B21E-00-021"));
    }
}
