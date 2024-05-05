# Use Amazon Corretto 17 with Alpine as the base image
FROM amazoncorretto:17-alpine

# Set the working directory in the Docker image
WORKDIR /app

# Copy the jar file from your host to your image filesystem
COPY target/*.jar /app/my-application.jar

# Command to run the application
CMD ["java", "-jar", "/app/my-application.jar"]

# Expose the port the app runs on
#EXPOSE 8005