package io.manudev.test3it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@EntityScan(basePackages = "io.manudev.test3it.core.adapter.out.persistence")
public class Test3ItApplication {

    public static void main(String[] args) {
        SpringApplication.run(Test3ItApplication.class, args);
    }

}
