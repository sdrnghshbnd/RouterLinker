FROM openjdk:21-slim

WORKDIR /app

COPY pom.xml .

# Copy the project source
COPY src ./src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean package

CMD ["java", "-jar", "target/RouterLinker-1.0-SNAPSHOT-jar-with-dependencies.jar"]