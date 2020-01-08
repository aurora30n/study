package study.springbootsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
public class SpringbootSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSessionApplication.class, args);
    }

}
