# Projeto Exemplo de Cadastro de Pessoas (Desktop - Swing)

Este projeto é um exemplo de aplicação de cadastro de pessoas, desenvolvido para a disciplina de Programação Orientada a Objetos (POO). Ele utiliza a biblioteca Swing para criar uma interface gráfica (GUI) simples onde é possível adicionar, editar e remover pessoas, e os dados são armazenados em um banco de dados SQLite.

## Funcionalidades

- Listar pessoas cadastradas
- Adicionar novas pessoas
- Editar pessoas existentes
- Excluir pessoas com confirmação antes da exclusão

## Estrutura do Projeto

- **`src/com/aula_desktop/`**: Contém os arquivos principais do projeto:
  - `Main.java`: Inicia o programa e conecta a visualização com o controlador.
  - `Pessoa.java`: Classe que representa o modelo de uma pessoa.
  - `PessoaDAO.java`: Classe responsável pela conexão e operações no banco de dados SQLite.
  - `PessoaController.java`: Controlador que gerencia as interações entre a interface gráfica e o banco de dados.
  - `PessoaView.java`: Interface gráfica utilizando Swing.

## Pré-requisitos

- **JDK 11** ou superior
- **SQLite JDBC Driver** (incluído no projeto)

## Como Executar

### Via Linha de Comando

1. Compile o projeto:

    ```bash
    javac -d bin -sourcepath src src/com/aula_desktop/*.java
    ```

2. Execute o programa:

```bash
    java -cp bin com.aula_desktop.Main
```

## Via IDE (Eclipse, IntelliJ)

Importe o projeto para sua IDE de preferência.
Certifique-se de que todas as dependências estão configuradas corretamente.
Execute a classe Main.java.

## Observações

Este projeto é apenas um exemplo para fins educacionais, feito para as turmas de POO. Não é recomendado para uso em produção.

## Licença

Este projeto é de uso livre para fins acadêmicos.
