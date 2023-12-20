package com.kaartik.tradingenginejava.config;

import com.kaartik.tradingenginejava.service.logger.LoggerType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "logging")
public class LoggingConfiguration {

    private LoggerType loggerType;
    private TextLoggerConfiguration textLoggerConfiguration;

    @Getter
    @Setter
    public static class TextLoggerConfiguration {
        private String directory;
        private String filename;
        private String fileExtension;

//        public enum LoggerType {
//            CONSOLE, FILE, DATABASE
//        }

    }
}
