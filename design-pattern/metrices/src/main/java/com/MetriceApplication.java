package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2020/3/10 8:57 上午
 */
@ComponentScan("com.ant")
@SpringBootConfiguration
public class MetriceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetriceApplication.class, args);
    }
}
