package com.kaartik.tradingenginejava.service.logger;

public interface ILogger {
    void debug(String module, String message);
    void debug(String module, Exception e);

    void information(String module, String message);
    void information(String module, Exception e);

    void warning(String module, String message);
    void warning(String module, Exception e);

    void error(String module, String message);
    void error(String module, Exception e);
}
