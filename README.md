# Projeto de estudos da JPA + Hibernate 
projeto de estudo baseado nos cursos da Alura de JDBC, JPA e Hibernate com algumas modificações

## Cursos:
- Java e JDBC: Trabalhando com um banco de dados
- Persistência com JPA: Introdução ao Hibernate

## Tecnologias 

Tecnologias utilizadas nesse projeto:
* [Java 11+](https://www.oracle.com/br/java/technologies/javase-downloads.html)
* [Maven 3.8.1](https://maven.apache.org/download.cgi)
* [PostgreSQL](https://www.postgresql.org/download/)

## Instalação 
Clone o repositorio do projeto 
```bash 
  git clone https://github.com/leo-jansen/teste-hibernate.git
  cd teste-hibernate
```
Alterar as variaveis do banco de dados no arquivo ``persistence.xml``, no diretório `src/main/resources/META-INF/`, 
depois instale as dependencias do projeto
```bash 
  mvn clean install
```
Crie uma database `loja` no seu banco de dados. Execute o programa com o comando  
```bash 
  mvn exec:java -Dexec.mainClass="br.com.alura.App" 
```
ou importe para o sua IDE e execute o arquivo `App.java`
