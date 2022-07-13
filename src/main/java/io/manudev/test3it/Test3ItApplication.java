package io.manudev.test3it;

import io.manudev.test3it.common.utils.HelpersConfig;
import io.manudev.test3it.core.adapter.in.web.IMusicalStyleEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@EntityScan(basePackages = HelpersConfig.PERSISTENCE_PACKAGE)
public class Test3ItApplication {
    public static void main(String[] args) {
        SpringApplication.run(Test3ItApplication.class, args);
    }

}
