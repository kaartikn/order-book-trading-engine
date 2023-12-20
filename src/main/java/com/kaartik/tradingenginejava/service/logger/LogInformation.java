package com.kaartik.tradingenginejava.service.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class LogInformation {
    private LogLevel logLevel;
    private String module;
    private String message;
    private LocalDateTime now;
    private long threadId;
    private String threadName;
}

