# Teste Prático de Programação

Projeto desenvolvido para o teste prático de programação, implementado em Java com leitura de funcionários de um arquivo CSV (`employees.csv`).

## Requisitos Atendidos
1. Classe `Person` com nome (String) e data de nascimento (LocalDate).
2. Classe `Employee` que estende `Person`, com salário (BigDecimal) e função (String).
3. Classe `Main` que executa:
    - 3.1: Carrega funcionários de `employees.csv`.
    - 3.2: Remove o funcionário "João".
    - 3.3: Imprime funcionários com formatação de data (dd/MM/yyyy) e salário (milhar com ponto, decimal com vírgula).
    - 3.4: Aplica aumento de 10% nos salários.
    - 3.5 e 3.6: Agrupa e imprime funcionários por função.
    - 3.8: Imprime funcionários que fazem aniversário em outubro e dezembro.
    - 3.9: Imprime o funcionário mais velho (nome e idade).
    - 3.10: Imprime lista ordenada por nome.
    - 3.11: Imprime total dos salários.
    - 3.12: Imprime quantos salários mínimos (R$1212.00) cada funcionário recebe.

## Estrutura do Projeto
- `src/main/java/org/example/model/`: Classes de domínio (`Person`, `Employee`, `ModelException`).
- `src/main/java/org/example/service/`: Lógica de negócios (`EmployeeService`, `ServiceException`).
- `src/main/java/org/example/repository/`: Acesso a dados (`EmployeeRepository`).
- `src/main/java/org/example/utils/`: Utilitários (`Formatters`).
- `src/main/resources/`: Arquivo `employees.csv`.
- `src/test/` (opcional): Testes unitários com JUnit 5.

## Como Executar
### Com Maven
1. Certifique-se de ter Java 17+ e Maven instalados.
2. Faça o clone do projeto
    ```bash
   git clone https://github.com/GuilhermeW1/iniflex.git
3. Navegue até a pasta do projeto:
   ```bash
   cd iniflex
   mvn clean package
   java -jar target/iniflex-1.0-SNAPSHOT-jar-with-dependencies.jar