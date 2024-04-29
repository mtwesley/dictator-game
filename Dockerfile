# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from your target directory to the working directory in the container
COPY ./target/Dictator-0.1-ALPHA.jar /app/dictator-game.jar

# Environment variables for the board
ENV BOARD_DEFAULT_WIDTH=30
ENV BOARD_DEFAULT_HEIGHT=30
ENV BOARD_DEFAULT_COIN_PROBABILITY=0.20
ENV BOARD_DEFAULT_TOTAL_COINS=10000
ENV BOARD_DEFAULT_MIN_COINS_PER_TILE=10
ENV BOARD_DEFAULT_MAX_COINS_PER_TILE=80

# Environment variables for the DB
ENV SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/dictator
ENV SPRING_DATA_MONGODB_DATABASE=dictator

# Environment variables for the server port
ENV SERVER_PORT=9000

# Environment variables for JWT
ENV JWT_BASE64_SECRET=c2VjcmV0
ENV JWT_SECRET=secret
ENV JWT_EXPIRATION=86400

# Expose the port the app runs on
EXPOSE 9000

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/dictator-game.jar"]
