# MapData API

## Overview
The **MapData API** is a Spring Boot-based RESTful API designed to manage map data and associated metadata. It provides endpoints for CRUD operations on map data and metadata, with a focus on scalability and ease of integration.

## Features
- RESTful API for managing map data and metadata.
- CRUD operations for both map data and metadata.
- Integration with a relational database using Spring Data JPA.
- Dockerized for easy deployment.

## Project Structure
- **`src/main/java`**: Contains the main application code.
- **`src/test/java`**: Contains unit and integration tests.
- **`docker/`**: Contains Docker configuration files for containerization.
- **`pom.xml`**: Maven configuration file for managing dependencies and build processes.

## Main Libraries and Frameworks
- **Spring Boot**: Framework for building the API.
- **Spring Data JPA**: For database interaction.
- **Lombok**: To reduce boilerplate code.
- **Maven**: For dependency management and build automation.

## Endpoints

### Map Data Endpoints
- **GET `/api/mapdata/all`**: Retrieve all map data.
- **GET `/api/mapdata/{id}`**: Retrieve a specific map data entry by ID.
- **POST `/api/mapdata`**: Create a new map data entry.
- **PUT `/api/mapdata/{id}`**: Update an existing map data entry.
- **DELETE `/api/mapdata/{id}`**: Delete a specific map data entry by ID.
- **DELETE `/api/mapdata/all`**: Delete all map data entries.

### Metadata Endpoints
- **GET `/api/mapdata/{id}/metadata`**: Retrieve all metadata for a specific map data entry.
- **GET `/api/mapdata/{id}/metadata/{idmetadata}`**: Retrieve a specific metadata entry by ID.
- **POST `/api/mapdata/{mapdataId}/metadata`**: Create a new metadata entry for a specific map data entry.
- **PUT `/api/mapdata/{mapdataId}/metadata/{metadataId}`**: Update a specific metadata entry.
- **DELETE `/api/mapdata/{mapdataId}/metadata/{metadataId}`**: Delete a specific metadata entry.
- **DELETE `/api/mapdata/{mapdataId}/metadata/all`**: Delete all metadata for a specific map data entry.

## Docker Setup
The project includes Docker support for easy deployment.

### Docker Files
- **`docker-compose.yml`**: Defines the services and configurations for running the application in a containerized environment.
- **`Dockerfile`**: Builds the Docker image for the application.

### Running with Docker
1. Build the Docker image:
   ```bash
   docker build -t mapdata-api .
   ```
2. Start the services using Docker Compose:
   ```bash
   docker-compose up -d
   ```
3. Access the API at `http://localhost:<port>` (default port is defined in the `docker-compose.yml` file).

### Stopping the Services
To stop the running containers:
```bash
docker-compose down
```

## Requirements
- Java 17 or higher.
- Maven 3.6 or higher.
- Docker and Docker Compose.

## How to Build and Run
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd mapdata-api
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   java -jar target/mapdata-api-0.0.1-SNAPSHOT.jar
   ```