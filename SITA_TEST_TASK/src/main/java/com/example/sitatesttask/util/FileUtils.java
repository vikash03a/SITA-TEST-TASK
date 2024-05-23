package com.example.sitatesttask.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static void moveFile(File file, Path targetDirectory, String suffix) throws IOException {
        Path targetPath = targetDirectory.resolve(file.getName() + suffix);
        Files.move(file.toPath(), targetPath);
    }
}
