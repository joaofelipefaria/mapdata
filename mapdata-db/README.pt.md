# mapdata-db

## Visão Geral
O **mapdata-db** é um projeto de gerenciamento de banco de dados projetado para lidar com a criação e manutenção do banco de dados para a aplicação **mapdata**. Ele fornece operações para criar tabelas e objetos associados, como sequências. A configuração do projeto permite especificar a operação desejada diretamente no arquivo **pom.xml**.

## Funcionalidades
- Criação de tabelas de banco de dados e objetos relacionados (por exemplo, sequências)
- Configuração fácil de operações de banco de dados via Maven
- Suporte para configuração e inicialização estruturada do banco de dados

## Uso
### Configurando Operações
As operações para gerenciar o banco de dados são configuradas no arquivo **pom.xml**. Você pode especificar qual operação executar modificando as configurações relevantes.

### Executando a Configuração do Banco de Dados
Para executar as operações de banco de dados, use o seguinte comando Maven:
```sh
mvn clean install
```
Certifique-se de que as configurações apropriadas estejam definidas no **pom.xml** antes de executar este comando.

## Requisitos
- Java (versão JDK compatível com o Maven)
- Maven
- Sistema de banco de dados compatível com a aplicação **mapdata**