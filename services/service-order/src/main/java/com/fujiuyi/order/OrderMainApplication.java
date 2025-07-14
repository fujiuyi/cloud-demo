package com.fujiuyi.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class OrderMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMainApplication.class, args);
    }


    /**
     * 使用nacosConfigManager监听配置的变化：
     * 1.项目启动，监听配置文件
     * 2.项目发生变化后拿到值
     * 3.发送邮件
     *
     * @param nacosConfigManager
     * @return
     */
    @Bean
    public ApplicationRunner applicationRunner(NacosConfigManager nacosConfigManager) {
        log.info("开始监听配置文件");
        return args -> {
            ConfigService service = nacosConfigManager.getConfigService();
            service.addListener("service-order.properties", "DEFAULT_GROUP", new Listener() {
                @Override
                public Executor getExecutor() {
                    return Executors.newFixedThreadPool(4);
                }

                @Override
                public void receiveConfigInfo(String s) {
                    log.info("监听到配置变化：{}", s);
                }
            });
        };
    }
}
