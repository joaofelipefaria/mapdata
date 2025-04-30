# Criar um ambiente virtual
python -m venv venv
source venv/bin/activate  # No Windows: venv\Scripts\activate

python -m pip install --upgrade pip

# Instalar Flask e outras dependências
pip install flask flask-sqlalchemy flask-migrate flask-cors

# Salvar as dependências no arquivo requirements.txt
pip freeze > requirements.txt

Explicação do Código
__tablename__:

Define o nome da tabela no banco de dados para cada modelo.
id:

Chave primária para identificar cada registro.
value, value1, value2:

Colunas do tipo String para armazenar os valores.
Relacionamento:

O modelo Data tem um relacionamento com Metadata usando db.relationship.
O modelo Metadata referencia Data com uma chave estrangeira (db.ForeignKey).
__repr__:

Fornece uma representação amigável do objeto para depuração.
to_dict:

Método para converter os objetos em dicionários Python, facilitando a serialização para JSON.


# MapData Backend

## Requisitos
- Python 3.8 ou superior
- Flask
- SQLite (ou outro banco de dados configurado)

## Como Rodar
1. Clone o repositório:
   ```bash
   git clone <repository-url>
   cd mapdata-backend