
```
  ____       _ _   _     _       _______   _                                                _           _   _                 
 |  _ \     (_| | (_)   | |     |__   __| | |                                              (_)         | | (_)                
 | |_) |_ __ _| |_ _ ___| |__      | | ___| | ___  ___ ___  _ __ ___  _ __ ___  _   _ _ __  _  ___ __ _| |_ _  ___  _ __  ___ 
 |  _ <| '__| | __| / __| '_ \     | |/ _ | |/ _ \/ __/ _ \| '_ ` _ \| '_ ` _ \| | | | '_ \| |/ __/ _` | __| |/ _ \| '_ \/ __|
 | |_) | |  | | |_| \__ | | | |    | |  __| |  __| (_| (_) | | | | | | | | | | | |_| | | | | | (_| (_| | |_| | (_) | | | \__ \
 |____/|_|  |_|\__|_|___|_| |_|    |_|\___|_|\___|\___\____|_______|_|_| |_| |_|\__,_|_| |_|_|\___\__,_|\__|_|\___/|_| |_|___/ 



                                                         ____ _______ 
                                                        |  _ |__   __|
                                                        | |_) | | |   
                                                        |  _ <  | |   
                                                        | |_) | | |   
                                                        |____/  |_| 


```

# Router Linker Application

## Overview
This Java application provides a list of linked routers.


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

Create test report with:
```
mvn clean test surefire-report:report
```
and then find the report in :
`target/surefire-reports/surefire-report.html`
