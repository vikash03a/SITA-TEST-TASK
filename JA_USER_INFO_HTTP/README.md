# JA_USER_INFO_HTTP

## Overview
This project is a Spring Boot web service that accepts a username as a query parameter, checks if the user exists in a properties file, and then posts user details to another web service.

## Prerequisites
- Java 17  
- Maven
-  IntelliJ

## Building the Project
1. Clone the repository:
    
2. Navigate to the project directory:
     
3. Build the project using Maven:
    
    mvn clean install
     

## Running the Project
1. Run the Spring Boot application:
    ```
    mvn spring-boot:run
    ```
2. Access the web service at:
    ```
    http://localhost:8080/appName/userDetail?user=admin
    ```

## Testing
1. Run the tests using Maven:
    ```
    mvn test
    ```

## Configuration
- `application.properties`: Contains configuration properties such as server port and external service URL.
- `users.properties`: Contains user data.

## Notes
- Ensure that the external service URL in `application.properties` is correctly set up.
- The application uses basic HTTP authentication  
