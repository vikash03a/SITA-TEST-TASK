package com.example.sitatesttask.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FileProcessorServiceTest {

    @Autowired
    private FileProcessorService fileProcessorService;

    @Test
    public void testProcessFile() throws IOException {
        Path inputDirectory = Paths.get("E:/SITA_TEST_TASK/IN");
        Path outputDirectory = Paths.get("E:/SITA_TEST_TASK/OUT");
        Path processedDirectory = Paths.get("E:/SITA_TEST_TASK/PROCESSED");
        Path errorDirectory = Paths.get("E:/SITA_TEST_TASK/ERROR");

        // Clean up directories before the test
        FileSystemUtils.deleteRecursively(inputDirectory);
        FileSystemUtils.deleteRecursively(outputDirectory);
        FileSystemUtils.deleteRecursively(processedDirectory);
        FileSystemUtils.deleteRecursively(errorDirectory);

        // Create directories
        Files.createDirectories(inputDirectory);
        Files.createDirectories(outputDirectory);
        Files.createDirectories(processedDirectory);
        Files.createDirectories(errorDirectory);

        // Create a test file
        Path testFile = inputDirectory.resolve("test.txt");
        Files.write(testFile, "1\n2\n3".getBytes());

        // Process the test file
        fileProcessorService.processFile(testFile.toFile());

        // Verify the output and processed files
        assertTrue(Files.exists(processedDirectory.resolve("test.txt.PROCESSED")));
        assertTrue(Files.exists(outputDirectory.resolve("test.txt.OUTPUT")));

        // Clean up processed and error directories after the test
        FileSystemUtils.deleteRecursively(processedDirectory);
        FileSystemUtils.deleteRecursively(errorDirectory);
    }
}
