package study.hbase;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author Jiguang Wang
 * @since 2021/3/16 16:05
 */
@ConfigurationProperties(prefix = "hbase")
public class HbaseProperties {
    private Map<String, String> config;
    public Map<String, String> getConfig() {
        return config;
    }
    public void setConfig(Map<String, String> config) {
        this.config = config;
    }
}