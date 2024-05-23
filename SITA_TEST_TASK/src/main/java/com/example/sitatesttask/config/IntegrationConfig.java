package com.example.sitatesttask.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Value("${input.directory}")
    private String inputDirectory;

    @Value("${output.directory}")
    private String outputDirectory;

    @Value("${processed.directory}")
    private String processedDirectory;

    @Value("${error.directory}")
    private String errorDirectory;

    @Bean
    public FileReadingMessageSource fileReadingMessageSource() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File(inputDirectory));
        source.setFilter(new SimplePatternFileListFilter("*.txt"));
        return source;
    }

    @Bean
    @ServiceActivator(inputChannel = "fileOutputChannel")
    public MessageHandler fileWritingMessageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(outputDirectory));
        handler.setExpectReply(false);
        handler.setFileNameGenerator(message -> message.getHeaders().get("file_name").toString() + ".OUTPUT");
        handler.setDeleteSourceFiles(true);
        return handler;
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedDelay(5000).get();
    }

    @Bean
    public IntegrationFlow fileIntegrationFlow() {
        return IntegrationFlows.from(fileReadingMessageSource(), config -> config.poller(poller()))
                .transform(File.class, p -> p)
                .handle("fileProcessorService", "processFile")
                .channel("fileOutputChannel")
                .get();
    }
}
