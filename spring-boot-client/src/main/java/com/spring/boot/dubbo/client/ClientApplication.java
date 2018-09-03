package com.spring.boot.dubbo.client;

import com.alibaba.dubbo.config.ConsumerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot 应用启动类
 * <p>
 * Created by bysocket on 16/4/26.
 */
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(false);
        consumerConfig.setTimeout(20000);
        return consumerConfig;
    }
}
