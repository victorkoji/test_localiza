# Localiza Teste

API desenvolvida para determinar o tempo que os veículos ficaram em determinados pontos de interesses.

## ⚙️ Pré-Requisitos instalados

- [Docker](https://www.docker.com/)
- [Python](https://www.python.org/)
- [Inmsonia](https://insomnia.rest/) ou [Postman](https://www.postman.com/)

## 🛠️ Instalação e execução

1. Executar o comando no terminal:
  ```sh
  docker-compose up
  ```
2. Depois que o servidor estiver rodando, será necessário popular o banco de dados.
   Para isso, execute o comando:
  ```sh
  python ./data/script_populate_database.py
  ```


## 🚀 Utilização da API
- Após a execução do projeto, você pode consultar a [**documentação da API**](http://localhost:8080/swagger-ui/index.html#/).
- Caso esteja usando o Inmsonia, você pode importar as rotas com o seguinte json.

## 💪 Construído com
- Java 8
- [Spring Boot](https://spring.io/projects/spring-boot)
- Hibernate
- JPA
- [Kotlin](https://kotlinlang.org/)
- TomCat
- [Swagger](https://swagger.io/specification/)

## 📜 Licença

Esse projeto está sob licença CC.
Para mais detalhes, veja o arquivo [LICENÇA Creative Commons Zero v1.0 Universal](https://choosealicense.com/licenses/cc0-1.0/).<br>
