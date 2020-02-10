package net.group.service.catalog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CatalogApplication {

    public static void main(String[] args) {
        log.info("CATALOG-APPLICATION STARTING ...");
        SpringApplication.run(CatalogApplication.class, args);
    }
}
