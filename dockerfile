# Use Java 17
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the project
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/item-api-0.0.1-SNAPSHOT.jar"]
