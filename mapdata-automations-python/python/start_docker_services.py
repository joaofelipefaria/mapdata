import subprocess

# Lista de diretórios contendo os arquivos docker-compose.yml
compose_dirs = [
    "../../mapdata-db/docker/",
    "../../mapdata-api/docker/",
    "../../mapdata-app/docker/",
    "../../mapdata-infra/git/docker/",
    #"../../mapdata-infra/jira/docker/",
    #"../../mapdata-infra/oauth/docker/",
    #"../../mapdata-infra/sonarqube/docker/"
]

# Comando base para rodar o Docker Compose
command = ["docker-compose", "up", "-d"]

# Loop para iniciar os serviços de cada projeto
for directory in compose_dirs:
    print(f"Starting Docker Compose in {directory}...")
    subprocess.run(command, cwd=directory, check=True)

print("All Docker Compose services started successfully!")
