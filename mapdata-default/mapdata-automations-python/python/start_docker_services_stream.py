import subprocess
import os

# Diretórios relativos ao local deste script
compose_dirs = [
    "../../mapdata-stream/docker/file-server/",
    "../../mapdata-stream/docker/kafka/"
]

# Caminho absoluto do diretório do script
base_dir = os.path.dirname(os.path.abspath(__file__))

# Comando base para rodar o Docker Compose
command = ["docker-compose", "up", "-d"]

# Loop para iniciar os serviços de cada projeto
for directory in compose_dirs:
    abs_dir = os.path.abspath(os.path.join(base_dir, directory))
    if not os.path.isdir(abs_dir):
        print(f"Directory does not exist: {abs_dir}")
        continue
    print(f"Starting Docker Compose in {abs_dir}...")
    subprocess.run(command, cwd=abs_dir, check=True)

print("All Docker Compose services started successfully!")