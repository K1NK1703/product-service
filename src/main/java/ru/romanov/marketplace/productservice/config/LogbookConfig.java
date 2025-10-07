package ru.romanov.marketplace.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultSink;
import org.zalando.logbook.core.DefaultHttpLogWriter;
import org.zalando.logbook.core.DefaultHttpLogFormatter;

@Configuration
public class LogbookConfig {

    @Bean
    public Logbook restLogbook() {
        return Logbook.builder()
                .sink(new DefaultSink(new DefaultHttpLogFormatter(), new DefaultHttpLogWriter()))
                .build();
    }
}
