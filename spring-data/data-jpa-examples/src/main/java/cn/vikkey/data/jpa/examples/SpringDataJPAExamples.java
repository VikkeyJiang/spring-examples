package cn.vikkey.data.jpa.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJPAExamples implements ApplicationRunner {

    public static final Logger logger = LoggerFactory.getLogger(SpringDataJPAExamples.class);


    public static void main(String[] args) {
        SpringApplication.run(SpringDataJPAExamples.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("for debug");
    }
}
