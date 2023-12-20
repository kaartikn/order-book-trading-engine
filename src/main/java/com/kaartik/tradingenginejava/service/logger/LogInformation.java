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
public record LogInformation(LogLevel logLevel, String module,
                             String message, LocalDateTime now, long threadId,
                             String threadName) {
}

