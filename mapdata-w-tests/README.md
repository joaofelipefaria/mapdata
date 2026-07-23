# MapData — with Tests

This folder is a variation of [`mapdata-default`](../mapdata-default) focused on **automated testing** of the backend service layer. It keeps the same base MapData API structure but adds a Mockito/JUnit 5 unit test suite for `MapDataService`.

## What's different from `mapdata-default`

- Same package layout and responsibilities (`controller`, `service`, `repository`, `entity`, `dto`, `config`).
- Adds `src/test/java/br/com/joaofelipefaira/mapdata/test/`:
  - `MapDataServiceTest` — a first, minimal Mockito-based test.
  - `MapDataServiceCompleteTest` — a full nested test suite (JUnit 5 `@Nested` classes) covering `MapDataService`'s CRUD operations: `GetAllMapData`, `GetMapDataById`, `DeleteById`, `DeleteAllMapData`, `Create`, and `Update`, including edge cases and argument-captor assertions on what gets persisted.
- Adds test-scoped dependencies: JUnit Jupiter, JUnit Platform Suite, Mockito JUnit Jupiter, plus Jacoco and Surefire plugins for coverage/test execution.

## Technologies used

- Java, Spring Boot (Web, Data JPA)
- PostgreSQL
- **JUnit 5** (Jupiter + Platform Suite)
- **Mockito** (`mockito-junit-jupiter`)
- **Jacoco** (test coverage reports)
- **Maven Surefire** (test runner)
- Maven, Docker

## How to run the tests

```bash
cd mapdata-api
mvn test
```

To generate a Jacoco coverage report:

```bash
mvn test jacoco:report
# report at target/site/jacoco/index.html
```

## How to run the application

Same flow as `mapdata-default` (this variant is not meant to replace it, only to demonstrate the test layer):

```bash
# 1) database
cd mapdata-api/docker
docker compose up -d   # requires the mapdata-network created by mapdata-db's compose stack

# 2) or run the API directly with Maven against a local Postgres instance
cd ../
mvn spring-boot:run
```

See the [repository-level README](../README.md) for the complete ecosystem startup order (database → dbadmin → API → frontend).
