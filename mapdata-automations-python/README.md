# MapData Automations Python

This project contains a collection of Python scripts to automate various tasks related to the MapData project, such as managing Docker services, building projects, and running environments.

## Project Structure

### Scripts Overview

#### `build_mapdata_api.py`
- **Description**: Placeholder script for building the `mapdata-api` project. The implementation details are not provided in the current context.

#### `run_env.py`
- **Description**: Orchestrates the execution of multiple scripts in a specific sequence to set up the environment.
- **Key Features**:
  - Executes scripts like starting Docker services, running database creation, and building projects.
  - Includes latency pauses between steps for better control.
  - Provides detailed logs for each step, including success or failure messages.

#### `run_mapdata_db_maven.py`
- **Description**: Placeholder script for running database creation tasks using Maven. The implementation details are not provided in the current context.

#### `shutdown_env.py`
- **Description**: Shuts down the environment by running specific scripts.
- **Key Features**:
  - Executes the `stop_docker_services.py` script to stop all Docker services.
  - Provides error handling and logs the output of the executed scripts.

#### `start_docker_services_api.py`
- **Description**: Starts Docker services for the `mapdata-api` project.
- **Key Features**:
  - Uses `docker-compose` to bring up services in detached mode.
  - Targets the `../../mapdata-api/docker/` directory.

#### `start_docker_services_app.py`
- **Description**: Starts Docker services for the `mapdata-app` project.
- **Key Features**:
  - Uses `docker-compose` to bring up services in detached mode.
  - Targets the `../../mapdata-app/docker/` directory.

#### `start_docker_services_db.py`
- **Description**: Starts Docker services for the `mapdata-db` project.
- **Key Features**:
  - Uses `docker-compose` to bring up services in detached mode.
  - Targets the `../../mapdata-db/docker/` directory.

#### `stop_docker_services.py`
- **Description**: Stops all running Docker services for specified projects.
- **Key Features**:
  - Uses `docker-compose` to stop services.
  - Targets directories like `../../mapdata-db/docker/`.

## How to Use

1. Clone the repository to your local machine.
2. Navigate to the `python/` directory.
3. Run the desired script using Python:
   ```bash
   python <script_name>.py
   ```
4. Follow the logs to monitor the execution.

## Notes

- Ensure Docker and Docker Compose are installed and configured on your system.
- Update the `compose_dirs` lists in the scripts to include or exclude specific services as needed.