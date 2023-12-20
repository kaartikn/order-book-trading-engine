package com.kaartik.tradingenginejava;

import com.kaartik.tradingenginejava.server.TradingEngineServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TradingEngineJavaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TradingEngineJavaApplication.class, args);
        TradingEngineServer engine = context.getBean(TradingEngineServer.class);
        engine.run();
    }

}
