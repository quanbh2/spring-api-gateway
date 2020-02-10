package net.group.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class UserApplication {

    public static void main(String[] args) {
        log.info("USER-APPLICATION STARTING ...");
        SpringApplication.run(UserApplication.class, args);
    }
}