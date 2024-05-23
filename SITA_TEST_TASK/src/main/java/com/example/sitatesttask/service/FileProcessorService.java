package com.example.sitatesttask.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileProcessorService {

    @Value("${processed.directory}")
    private String processedDirectory;

    @Value("${error.directory}")
    private String errorDirectory;

    public Message<File> processFile(File inputFile) {
        Path inputPath = inputFile.toPath();
        try {
            List<String> lines = Files.readAllLines(inputPath);
            int sum = lines.stream().mapToInt(Integer::parseInt).sum();
            String outputContent = String.valueOf(sum);

            // Creating output file
            Path outputPath = Paths.get(inputPath.toString() + ".OUTPUT");
            Files.write(outputPath, outputContent.getBytes());

            // Move input file to processed directory
            Files.move(inputPath, Paths.get(processedDirectory, inputFile.getName() + ".PROCESSED"));

            return null; // Actual return value can be customized
        } catch (Exception e) {
            try {
                Files.move(inputPath, Paths.get(errorDirectory, inputFile.getName() + ".ERROR"));
            } catch (IOException ioException) {
                throw new RuntimeException("Error moving file to error directory", ioException);
            }
            throw new RuntimeException("Error processing file", e);
        }
    }
}
