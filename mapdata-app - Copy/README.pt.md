# MapData App

## Overview
O **MapData App** é uma aplicação Angular que serve como interface de usuário para gerenciar dados de mapas e seus metadados. Ele consome a API RESTful do `mapdata-api` para realizar operações de CRUD em dados e metadados.

## Features
- Interface amigável para gerenciar dados de mapas e metadados.
- Operações de CRUD para dados e metadados.
- Integração com a API `mapdata-api`.
- Totalmente containerizado com suporte a Docker.

## Project Structure
- **`src/app/components/`**: Contém os componentes principais da aplicação, como:
  - **`data-list/`**: Lista e gerencia dados de mapas.
  - **`data-form/`**: Formulário para criar ou editar dados de mapas.
  - **`metadata-list/`**: Lista e gerencia metadados associados a dados de mapas.
  - **`metadata-form/`**: Formulário para criar ou editar metadados.
- **`src/app/services/`**: Contém os serviços para comunicação com a API.
- **`src/app/model/`**: Define os modelos de dados (`Data` e `Metadata`).
- **`docker/`**: Contém os arquivos de configuração do Docker.
- **`angular.json`**: Configuração do Angular CLI.
- **`package.json`**: Gerenciamento de dependências do Node.js.

## Main Libraries and Frameworks
- **Angular**: Framework para construção da interface de usuário.
- **RxJS**: Para manipulação de streams assíncronas.
- **Angular Forms**: Para gerenciamento de formulários.
- **Angular HttpClient**: Para comunicação com a API.
- **Bootstrap** (opcional): Para estilização.

## Endpoints Consumidos
A aplicação consome os seguintes endpoints da API `mapdata-api`:

### Dados de Mapas
- **GET `/api/mapdata/all`**: Recupera todos os dados de mapas.
- **GET `/api/mapdata/{id}`**: Recupera um dado de mapa específico pelo ID.
- **POST `/api/mapdata`**: Cria um novo dado de mapa.
- **PUT `/api/mapdata/{id}`**: Atualiza um dado de mapa existente.
- **DELETE `/api/mapdata/{id}`**: Remove um dado de mapa específico.

### Metadados
- **GET `/api/mapdata/{id}/metadata`**: Recupera todos os metadados associados a um dado de mapa.
- **POST `/api/mapdata/{id}/metadata`**: Cria um novo metadado associado a um dado de mapa.
- **PUT `/api/mapdata/{id}/metadata/{metadataId}`**: Atualiza um metadado existente.
- **DELETE `/api/mapdata/{id}/metadata/{metadataId}`**: Remove um metadado específico.

## Docker Setup
O projeto inclui suporte ao Docker para facilitar a implantação.

### Docker Files
- **`docker-compose.yml`**: Define os serviços e configurações para rodar a aplicação em um ambiente containerizado.
- **`Dockerfile`**: Constrói a imagem Docker da aplicação.

### Running with Docker
1. Construa a imagem Docker:
   ```bash
   docker build -t mapdata-app .
   ```
2. Inicie os serviços usando o Docker Compose:
   ```bash
   docker-compose up -d
   ```
3. Acesse a aplicação em `http://localhost:<port>` (a porta padrão está definida no arquivo `docker-compose.yml`).

### Stopping the Services
Para parar os containers em execução:
```bash
docker-compose down
```

## Requirements
- Node.js 16 ou superior.
- Angular CLI.
- Docker e Docker Compose.

## How to Build and Run
1. Clone o repositório:
   ```bash
   git clone <repository-url>
   cd mapdata-app
   ```
2. Instale as dependências:
   ```bash
   npm install
   ```
3. Execute a aplicação em modo de desenvolvimento:
   ```bash
   ng serve
   ```
4. Acesse a aplicação em `http://localhost:4200`.