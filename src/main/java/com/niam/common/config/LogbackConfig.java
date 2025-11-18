package com.niam.common.config;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogbackConfig {
    @Value("${common.logging-file.enable}")
    private boolean enableFileLogging;

    @Value("${common.logging-file.path}")
    private String logDirectory;

    @Value("${common.logging-file.max-file-size}")
    private String maxFileSize;

    @Value("${common.logging-file.total-size-cap}")
    private String totalSizeCap;

    @Value("${common.logging-file.max-history}")
    private String maxHistory;

    @Value("${common.logging-pattern.file}")
    private String filePattern;

    @Bean
    public Object configureFileLogging() {
        if (!enableFileLogging) {
            return null;
        }

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
        fileAppender.setContext(context);
        fileAppender.setName("RollingFile");
        fileAppender.setFile(logDirectory + "/logger.log");

        SizeAndTimeBasedRollingPolicy<ILoggingEvent> sizeAndTimePolicy = new SizeAndTimeBasedRollingPolicy<>();
        sizeAndTimePolicy.setContext(context);
        sizeAndTimePolicy.setMaxFileSize(FileSize.valueOf(maxFileSize));
        sizeAndTimePolicy.setFileNamePattern(logDirectory + "/archived/logger-%d{yyyy-MM-dd}.%i.log");
        sizeAndTimePolicy.setMaxHistory(Integer.parseInt(maxHistory));
        sizeAndTimePolicy.setTotalSizeCap(FileSize.valueOf(totalSizeCap));
        sizeAndTimePolicy.setParent(fileAppender);
        sizeAndTimePolicy.start();

        fileAppender.setRollingPolicy(sizeAndTimePolicy);
        fileAppender.setEncoder(getPatternLayoutEncoder(context, filePattern));
        fileAppender.start();

        Logger rootLogger = context.getLogger("ROOT");
        rootLogger.addAppender(fileAppender);

        return null;
    }

    private PatternLayoutEncoder getPatternLayoutEncoder(LoggerContext context, String pattern) {
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern(pattern);
        encoder.start();
        return encoder;
    }
}