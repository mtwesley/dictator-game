# Dictator Game API Back-End

This repository is the backend implementation of the Dictator Game, an experiment in behavioral economics. Players can take on the role of dictators or citizens, making strategic decisions about the allocation of resources.

## Overview

The game will features multiple levels, tiles, and dynamic roles, providing a rich platform for exploring decision-making, strategy, and social dynamics in an economic context.

## Objectives
- *Multiplayer support:* Engage multiple players in decision-making processes in real time.
- *Role dynamics:* Players can be dictators, citizens, or other positions with unique capabilities and restrictions.
- *Level progression:* Players advance through levels by winning tiles, each level with its own board layout and set of challenges.
- *Turn-based gameplay:* Players are given an opportunity tdrestoreo make moves sequentially to ensure fair and orderly participation for all players.
- *Data-driven improvements:* Decisions and outcomes are stored privately to improve future gameplay. 

## Technologies Used

- Spring Boot for the application framework.
- Spring Web for handling HTTP requests.
- Spring Data MongoDB for database interactions.
- Spring Security for authentication and authorization.
- WebSocket for real-time communication.
- JWT (JSON Web Tokens) for secure and stateless authentication.
- Lombok for reducing boilerplate code.

## Getting Started

These instructions will get your copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- JDK 11
- Maven
- MongoDB

### Running the Application

1. Clone the repository:
   ```
   git clone https://github.com/mtwesley/dictator-game
   ```
2. Navigate to the project directory:
   ```
   cd dictator-game
   ```
3. Compile and package the application using Maven:
   ```
   mvn package
   ```
4. Run the application:
   ```
   java -jar target/dictator-0.1-ALPHA.jar
   ```

## Configuration

The application's configurations are managed through the `application.properties` file.

```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/dictator
spring.data.mongodb.database=dictator

# Server port configuration
server.port=9000

# JWT configuration
jwt.secret=YourJWTSecretKey
jwt.expiration=86400
```

## API Endpoints

The server exposes various endpoints for game operations, such as:

- `POST /login`: Authenticate users and retrieve a JWT.
- `POST /register`: Register new users.
- `GET /board`: Retrieve game board data.
- `POST /game`: Submit game actions.

## Testing

Run the test suite using Maven:

```
mvn test
```

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

- Spring Boot Team for the amazing framework.
- All contributors and players who test and use this game.
