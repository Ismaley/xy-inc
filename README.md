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
docker-compose -f docker-dev.yml up
./mvnw clean install
```

2. Testes

```bash
docker-compose -f docker-dev.yml up
./mvnw test
```

3. Documentação

```bash
# após fazer o build do projeto navegar até o diretório ../xy-locator-web/target/generated-docs/ e abrir o arquivo index.html
```



