package com.kaartik.tradingenginejava.server;

import com.kaartik.tradingenginejava.config.TradingEngineServerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

@Service
public class TradingEngineServer implements ITradingEngineServer {

    public static final Logger logger = LoggerFactory.getLogger(TradingEngineServer.class);
    private final TradingEngineServerConfiguration config;

    public TradingEngineServer(TradingEngineServerConfiguration config) {
        if (config == null) {
            throw new IllegalArgumentException("Config cannot be null");
        }
        this.config = config;
    }

    //TODO: Start trading engine
    @Async
    public CompletableFuture<Void> run() throws CancellationException {
        return CompletableFuture.runAsync(() -> {
            try {
                logger.info("Starting Trading Engine");
            } catch (Exception e) {
                logger.error("An error occurred in the Trading Engine", e);
            } finally {
                logger.info("Stopping Trading Engine");
            }
        });
    }
}
