package cn.vikkey.security.oauth.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityOauthServerApplication implements ApplicationRunner {

    public static final Logger logger = LoggerFactory.getLogger(SecurityOauthServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SecurityOauthServerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("application running");
    }
}
