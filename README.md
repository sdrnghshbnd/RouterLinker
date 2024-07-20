## Prerequisites

- Java 21
- Maven
- Docker (optional, for containerization)

## Building and Running the Project

### Using Maven

1. **Build the project**:
    ```
    mvn clean package
    ```

2. **Run the application**:
    ```
    java -jar target/RouterLinker-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```

### Using Docker

1. **Build the Docker image**:
    ```sh
    docker build -t routerlinker .
    ```

2. **Run the Docker container**:
    ```sh
    docker run -it --rm routerlinker
    ```

## Configuration

Configuration properties are stored in the `config.properties` file located in the `src/main/resources` directory. If you want to test the project against a different API, you can change the API_URL.

## Logging

Logging configuration is handled by `log4j2.xml`, located in the `src/main/resources` directory. You can adjust the logging levels and appenders as needed.

## Running Tests

Run tests with Maven: `mvn test`
