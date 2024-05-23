# SITA_TEST_TASK

This project is a solution to the SITA test task, which involves processing files in a directory and performing certain operations on them.

## Overview

The application monitors a specified directory for new text files, each containing numbers separated by new lines. Upon finding a new file, it sums up all the numbers in it and creates a new file with the resulting value in another directory. Depending on the outcome of the processing, the original input file is then moved to either a "processed" directory or an "error" directory.

## Project Structure

The project follows a standard Maven project structure. Here's an overview:

- `src/main/java`: Contains the Java source files.
- `src/main/resources`: Contains configuration files such as application.properties.
- `src/test/java`: Contains unit test classes.
- `src/test/resources`: Contains resources for unit testing.

## Components

- **FileProcessorService**: Defines the contract for processing files.
- **FileProcessorServiceImpl**: Implements the FileProcessorService interface.
- **IntegrationConfig**: Spring Integration configuration for polling files from the input directory.
- **FileMessageHandler**: Message handler for processing files.
- **FileUtil**: Utility class for file-related operations.

## Dependencies

This project uses the following dependencies:

- Spring Boot Starter Web
- Spring Boot Starter Test
- Spring Integration Core

## Configuration

- `application.properties`: Contains configurable properties such as input/output directory paths.

## Building and Running

 

