package io.reflectoring.buckpal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@EntityScan(basePackages = "io.reflectoring.buckpal.account.adapter.out.persistence")
public class BuckPalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuckPalApplication.class, args);
    }

}
