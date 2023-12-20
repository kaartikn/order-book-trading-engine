package com.kaartik.tradingenginejava.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

public class TradingEngineServer implements ITradingEngineServer {

    public static final Logger logger = LoggerFactory.getLogger(TradingEngineServer.class);

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
