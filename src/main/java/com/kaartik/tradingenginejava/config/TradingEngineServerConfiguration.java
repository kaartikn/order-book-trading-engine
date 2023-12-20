package com.kaartik.tradingenginejava.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tradingengineserver")
public class TradingEngineServerConfiguration {

    private TradingEngineServerSettings tradingEngineServerSettings;

    public TradingEngineServerSettings getTradingEngineServerSettings() {
        return tradingEngineServerSettings;
    }

    public void setTradingEngineServerSettings(TradingEngineServerSettings tradingEngineServerSettings) {
        this.tradingEngineServerSettings = tradingEngineServerSettings;
    }

    public static class TradingEngineServerSettings {

        private int port;

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
