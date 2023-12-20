package com.kaartik.tradingenginejava.service.logger;

import com.kaartik.tradingenginejava.config.LoggingConfiguration;

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

    private final LoggingConfiguration loggingConfiguration;
    private final BlockingQueue<LogInformation> logQueue = new LinkedBlockingQueue<>();
    private final Thread logThread;
    private final AtomicBoolean disposed = new AtomicBoolean(false);

    public TextLogger(LoggingConfiguration loggingConfiguration){
        if (loggingConfiguration == null) {
            throw new IllegalArgumentException("loggingConfiguration cannot be null");
        }
        if (loggingConfiguration.getLoggerType() != LoggerType.TEXT) {
            throw new IllegalStateException("Wrong Logger Type");
        }

        this.loggingConfiguration = loggingConfiguration;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String logDirectory = Paths.get(loggingConfiguration.getTextLoggerConfiguration().getDirectory(), dateFormat.format(new Date())).toString();
        new File(logDirectory).mkdirs();
        String baseLogName = String.format("%s-%s.%s", loggingConfiguration.getTextLoggerConfiguration().getFilename(),
                new SimpleDateFormat("HH_mm_ss").format(new Date()),
                loggingConfiguration.getTextLoggerConfiguration().getFileExtension());
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
