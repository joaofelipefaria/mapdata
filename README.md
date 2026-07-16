# MapData

This repository brings together different versions of the MapData project, each representing a variation of the same ecosystem with a specific focus.

The main idea is to show how the same domain can evolve incrementally:
- a base project with the main system structure;
- complementary versions that add features such as JWT authentication, file upload, and Python integration.

## Base project: mapdata-default

The most complete and reference project in the repository is MapData Default. It represents the main version of the system and contains the core functional base of the application.

### What it does
MapData Default is a full-stack application for data management with a web interface, a backend API, and a database. It allows:
- registering and querying records;
- organizing data through a REST API;
- displaying information in a web interface;
- connecting the backend to a PostgreSQL database.

### Main structure
- mapdata-api: backend in Java/Spring Boot responsible for the REST API and business logic.
- mapdata-app: frontend in Angular for the user interface.
- mapdata-web: static web part, useful for simple visualization or base resources (vanilla JS).
- mapdata-db: component related to the database.
- mapdata-dbadmin: tool for database administration.
- mapdata-automations-python: Python scripts for automating environment tasks.

### Purpose of the default project
This project is the starting point of the MapData ecosystem. It contains the basic structure of the system and serves as a reference for the other versions, which only change specific aspects.

---

## Other project versions

The other folders in the repository do not replace the base project. They represent derived versions where only some components were changed or expanded to demonstrate additional functionalities.

### mapdata-jwt
This project is a variation of MapData Default with JWT authentication.

It keeps the same base purpose of the system, but adds:
- user login and authentication;
- JWT token generation and validation;
- protection of API endpoints.

In other words, it is the same base project, but with security added.

### mapdata-fileupload
This version is an extension of the base project with file upload support.

It preserves the main structure of MapData, but includes features for:
- sending files to the system;
- processing files through the backend;
- integrating this flow with the rest of the application.

In short, it is the same ecosystem, focused on upload and file handling.

### mapdata-python
This folder contains a variation where the backend is implemented in Python instead of Java/Spring Boot.

The main difference is that the core API layer is built with Python, while the rest of the project remains aligned with the same overall MapData concept. This version mainly focuses on:
- a Python-based backend implementation;
- the same domain and data flow as the base project;
- an alternative technical stack for the API layer;
- a hexagonal architecture approach with market-aligned design patterns.

In short, this version keeps the same purpose as the base project, but uses Python for the backend implementation and follows a cleaner, more modular architecture inspired by current software engineering best practices.

---

## How to think about the projects

In simple terms:
- mapdata-default = main and most complete version;
- mapdata-jwt = same base, but with authentication;
- mapdata-fileupload = same base, but with file uploads;
- mapdata-python = same base, but with Python back-end.

Each of these folders represents a specific evolution of the same project, rather than a completely different solution.

---

## Running the project

To run the base flow of MapData, follow these steps:

### 1. Start the database container
Go to the database project and start the container:

```bash
cd mapdata-default/mapdata-db
docker compose up -d
```

This starts the PostgreSQL database used by the application.

### 2. Create the database
Use the database administration project to create the necessary database schema or initial structure. This project runs with Maven:

```bash
cd mapdata-default/mapdata-dbadmin
mvn clean compile exec:java
```

Open the available admin interface or follow the project-specific instructions to initialize the database.

### 3. Start the API
Start the backend service:

```bash
cd mapdata-default/mapdata-api
mvn spring-boot:run
```

The API will be available on the default Spring Boot port.

### 4. Start the frontend
Start the Angular frontend:

```bash
cd mapdata-default/mapdata-app
npm install
npm start
```

The frontend will be available in the browser through the Angular development server.

### 5. Optional: use the Python automation project
If you want to start everything automatically, the Python automation project contains scripts that can launch the environment using Docker containers.

Example:

```bash
cd mapdata-default/mapdata-automations-python
python run_env.py
```

These scripts are intended to simplify the startup process for the whole environment.

---

## Notes

- Make sure Docker is installed and running before starting the database containers.
- The API and frontend should be started after the database is available.
- If you are using the JWT or file upload variants, follow the same startup flow, but with the corresponding project folder.