package com.kaartik.tradingenginejava;

import com.kaartik.tradingenginejava.server.TradingEngineServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TradingEngineJavaApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(TradingEngineJavaApplication.class, args);
//        TradingEngineServer engine = context.getBean(TradingEngineServer.class);
//        engine.run();

        SpringApplication.run(TradingEngineJavaApplication.class, args);
        TradingEngineServer tradingEngineServer = new TradingEngineServer();
        tradingEngineServer.run();

    }

}
