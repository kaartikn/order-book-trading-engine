package com.kaartik.tradingenginejava.service.logger;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class TextLogger extends AbstractLogger implements ITextLogger {

    private final BlockingQueue<LogInformation> logQueue = new LinkedBlockingQueue<>();
    private final Thread logThread;
    private final AtomicBoolean disposed = new AtomicBoolean(false);

    @Value("${logging.text-logger.directory}")
    private String logDirectoryProperty;

    @Value("${logging.text-logger.filename}")
    private String baseFilenameProperty;

    @Value("${logging.text-logger.file-extension}")
    private String fileExtensionProperty;

    public TextLogger(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String logDirectory = Paths.get(logDirectoryProperty, dateFormat.format(new Date())).toString();
        new File(logDirectory).mkdirs();

        String baseLogName = String.format("%s-%s.%s", baseFilenameProperty,
                new SimpleDateFormat("HH_mm_ss").format(new Date()),
                fileExtensionProperty);

        Path filepath = Paths.get(logDirectory, baseLogName);

        logThread = new Thread(() -> {
            try {
                logAsync(filepath.toString());
            } catch (IOException e) {
                // Handle IOException
            }
        });
        logThread.start();
    }


    @PreDestroy
    public void close() throws Exception {
        if (!disposed.getAndSet(true)) {
            logThread.interrupt();
            logThread.join();
        }
    }

    private void logAsync(String filepath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    LogInformation logItem = logQueue.take();
                    String formattedMessage = formatLogItem(logItem);
                    writer.write(formattedMessage);
                    writer.newLine();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private String formatLogItem(LogInformation logItem) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSSSSS");
        return String.format("[%s] [%-30s : %03d][%s] %s",
                dateFormat.format(logItem.getNow()),
                logItem.getThreadName(),
                logItem.getThreadId(),
                logItem.getLogLevel(),
                logItem.getMessage());
    }

    @Override
    public void log(LogLevel loglevel, String module, String message) {
        logQueue.add(new LogInformation(loglevel, module, message, LocalDateTime.now(),
                Thread.currentThread().getId(), Thread.currentThread().getName()));
    }

}
