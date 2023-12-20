package com.kaartik.tradingenginejava.service.logger;


public abstract class AbstractLogger implements ILogger {
    public void debug(String module, String message) {
        log(LogLevel.DEBUG, module, message);
    }

    public void debug(String module, Exception e) {
        log(LogLevel.DEBUG, module, e.toString());
    }

    public void error(String module, String message) {
        log(LogLevel.ERROR, module, message);
    }

    public void error(String module, Exception e) {
        log(LogLevel.ERROR, module, e.toString());
    }

    public void information(String module, String message) {
        log(LogLevel.INFORMATION, module, message);
    }

    public void information(String module, Exception e) {
        log(LogLevel.INFORMATION, module, e.toString());
    }

    public void warning(String module, String message) {
        log(LogLevel.WARNING, module, message);
    }

    public void warning(String module, Exception e) {
        log(LogLevel.WARNING, module, e.toString());
    }

    public abstract void log(LogLevel logLevel, String module, String message);

}
