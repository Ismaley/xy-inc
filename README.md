# XY Location Services

## Requisitos Mínimos

- Docker
- Docker-compose

## Execução

```bash
# executar os comandos na raiz do projeto
./build-local-images.sh
docker-compose up
```
1. Build do projeto

```bash
./mvnw clean install
```

2. Testes

```bash
./mvnw test
```

2. Documentação

após fazer o build do projeto navegar até o diretório /xy-locator-web/target/generated-docs/index.html

