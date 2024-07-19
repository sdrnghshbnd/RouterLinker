# Use the official OpenJDK 21 image as a parent image
FROM openjdk:21-slim

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Copy the project source
COPY src ./src

# Copy the target directory (if you've already built the project)
COPY target ./target

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean package

# Specify the command to run on container start
CMD ["java", "-jar", "target/RouterLinker-1.0-SNAPSHOT.jar"]