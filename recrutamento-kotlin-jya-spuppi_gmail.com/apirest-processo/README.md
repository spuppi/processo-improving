# Octo Events PROJETO SULLYVAN PUPPI

## 1. Clone repositorio

git clone https://SullyvanPuppi@bitbucket.org/recrutamento_jya_kotlin/recrutamento-kotlin-jya-spuppi_gmail.com.git

Diretorio do projeto:
apirest-processo


## 2. Configuração do banco de dados PostgreSQL

Class de configuração de acordo com o ambiente trabalhado
Deve ser configurado o perfil desejado no application.properties
spring.profiles.active=dev

ATIVO
@Profile("dev")
ConfigDev


## 3. Webhook git para testes

https://github.com/spuppi/processo-improving

Secret configurada
X-Hub-Signature: g8IViF7xjYaJVByDoG


## 4. Configuração do banco de dados PostgreSQL

datasource.url=jdbc:postgresql://localhost:5432/processo_db
datasource.username=processo_usr
datasource.password=5gxq20vjma0p


## 5. Tests unitários com JUnit

com.spuppi.apirestdemo.ApirestDemoApplicationTests

listEventsByIssue()
Lista todos os eventos de acordo com issue informada

addEvent()
Adiciona event conforme arquivo event.json local
(/apirest-processo/src/test/java/event.json)


## 5. Doc da API

Documentação da API disponível via Swagger

http://localhost:8080/api/swagger-ui.html


POST - /issues
GET - /issues/{issue}/events
GET - /issues/{issue}/events/documents
GET - /issues/events/login/{login}

## 6. Comandos úteis

/home/puppi/DEV/ngrok http 8080


Atualizar webhook do git


Start springapp


sudo -u postgres createuser --interactive


sudo adduser processo_usr


Acessar banco postgres


sudo -i -u processo_usr


createdb processo_db


psql processo_db


ALTER USER processo_usr WITH PASSWORD '5gxq20vjma0p';


Acessar banco mongo


mongo


db.createCollection('processo_db')


db.createUser({user: "processo_usr",pwd: "5gxq20vjma0p",roles: [{ role:"readWrite", db:"processo_db" }]})


use processo_db


Ex. de busca: db.getCollection('EVENTS').find({"content.issue.user.login":"spuppi"})

## 7. Recursos adicionados

Segurança: Adicionado validação da chave sha256 de acordo com key secret cadastrado no Github e ajustado no application.properties


Banco de dados NoSQL: Adicioando conexão com o MongoDB ajustado pelo application.properties


Adicionado endpoints de consultado ao MongoDB


Adicionado testes unitários de conexão com o MongoDB com parser das respostas


Ajustes nos códigos de Status HTTP response