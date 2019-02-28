# Busque restaurantes do bairro pela localização usando MongoDB e Redis

Neste desafio você criará um endpoint para buscar os restaurantes do bairro onde o usuário está localizado e salvar os restaurantes do bairro no Redis para as próximas requisições.

## Tópicos

Neste desafio você aprenderá:

* Java
* Spring MVC
* MongoDB
* Redis

## Requisitos

Para este desafio você precisará de:

* Java 8 (ou superior)
* Git
* MongoDB 4.0 (ou superior)
* Redis 4.0.0 (ou superior)

## MongoDB

Instalar o MongoDB e rodar na porta padrão `27017`

https://docs.mongodb.com/manual/installation

**Importar a base de restaurantes e bairros:**
```
mongoimport <caminho para restaurants.json> -d challenge -c restaurant
mongoimport <caminho para neighborhoods.json> -d challenge -c neighborhood
```

#### Collection `restaurant`

Possui o nome e a localização do restaurante.

```
{
   location: {
      type: "Point",
      coordinates: [-73.856077, 40.848447]
   },
   name: "Morris Park Bake Shop"
}
```

#### Collection `neighborhood`

Possui o nome e a área do bairro.

```
{
   geometry: {
      type: "Polygon",
      coordinates: [[
         [ -73.99, 40.75 ],
         ...
         [ -73.98, 40.76 ],
         [ -73.99, 40.75 ]
      ]]
    },
    name: "Hell's Kitchen"
}
```

## Redis

Instalar o Redis e rodar na porta padrão `6379`

Linux/OSX: https://redis.io/download#installation

Windows: https://github.com/ServiceStack/redis-windows

## Detalhes

Criar um enpoint para encontrar o bairro em que um usuário está localizado utilizando sua localização. Verificar se o bairro está salvo no Redis e retornar caso positivo. Caso o bairro não esteja salvo, achar todos os restaurantes localizados neste bairro. Salvar no Redis o bairro e seus restaurantes e retornar.


##### Valor a ser salvo no Redis

Salvar o valor na seguinte `key`:
* `neighborhood:{id}` Ex: "neighborhood:55cba2476c522cafdb058ea1"

O valor do `key` deve ser uma String no seguinte formato `JSON`:
```json
{
  "id": "55cb9c666c522cafdb053a53",
  "name": "Nome do Bairro",
  "restaurants": [
    {
      "id": "55cba2476c522cafdb057e86",
      "name": "Fu Wing Kitchen",
      "x": -73.945672,
      "y": 40.7923029
    },
    {
      "id": "55cba2476c522cafdb05712f",
      "name": "Gong Thai Restaurant",
      "x": -73.94827529999999,
      "y": 40.78741429999999
    }
  ]
}
```

---

Endpoint a ser desenvolvido:

## GET /restaurants/findInNeighborhood

Lista os restaurantes do bairro de acordo com as coordenadas geográficas.
* Encontrar o bairro no MongoDB de acordo com as coordenadas geográficas
* Se está salvo no Redis retornar no response body
* Se não está salvo no Redis, encontrar todos os restaurantes deste bairro no MongoDB, salvar no Redis e retornar no response body 
* Ordenar os restaurantes pelo campo `name` em ordem alfabética ascendente.
* Ex: /restaurants/findInNeighborhood?x=-73.9506635&y=40.7858944

###### Algumas localizações válidas para teste:
* x=-73.9506635 y=40.7858944
* x=-74.1451407 y=40.598308
* x=-74.0150371 y=40.6452228
* x=-74.0102542 y=40.7196048
* x=-73.921833 y=40.8205969

##### Request param
* `x` - Longitude
* `y` - Latitude

##### Response body
```json
{
  "id": "55cb9c666c522cafdb053a53",
  "name": "Nome do Bairro",
  "restaurants": [
    {
      "id": "55cba2476c522cafdb057e86",
      "name": "Fu Wing Kitchen",
      "x": -73.945672,
      "y": 40.7923029
    },
    {
      "id": "55cba2476c522cafdb05712f",
      "name": "Gong Thai Restaurant",
      "x": -73.94827529999999,
      "y": 40.78741429999999
    }
  ]
}
```

---

### Rodando a aplicação

Executar o comando `gradlew bootRun`

A aplicação estará disponível em `http://localhost:8080`
