package com.kaartik.tradingenginejava.server;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

public interface ITradingEngineServer {
    CompletableFuture<Void> run() throws CancellationException;
}
