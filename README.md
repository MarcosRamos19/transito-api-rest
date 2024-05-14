# API de Trânsito 


## Sobre o projeto

Este projeto foi desenvolvido durante o curso Ignição Spring Rest da [AlgaWorks](https://https://www.algaworks.com/ "Site da AlgaWorks").

A aplicação consiste em uma API que é responsável pelo gerenciamento de um sistema simples de trânsito.

## 🚀 Tecnologias utilizadas
- Java
- Maven
- Spring Boot
- JPA / Hibernate
- SpringDoc / Swagger
- Flyway
- Jakarta Bean Validation
- SQL
  
## 💻 Como executar o projeto

Pré-requisitos: Java 17; Base de Dados MySql

 clonar repositório
```bash
git clone https://github.com/MarcosRamos19/transito-api-rest.git
```
 atualizar o arquivo	`aplication.properties` com as características do seu BD 
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/transito?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=seu usuário
spring.datasource.password=sua senha
```
## 📚 Documentação da API 

Depois de executar o projeto a documentação estará disponível em: `http://localhost:8080/swagger-ui/index.html`. 

### Documentação com Swagger
![Swagger](https://github.com/MarcosRamos19/transito-api-rest/blob/main/transito-api/images/swagger-transito.JPG)

## Modelo conceitual
![Modelo Conceitual](https://github.com/MarcosRamos19/transito-api-rest/blob/main/transito-api/images/diagramaTransito.JPG)


