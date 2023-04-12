#  Projeto Drone Feeder (FutureH) 
<div align="center">
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpL8tgwBXKIDLVDhX7HQe_shkDCnoZh-sH-g&usqp=CAU" width="300px" />
</div>

## Descrição do Projeto :package:

A empresa FutureH registrou uma nova patente que permitirá a entrega de pacotes com drones. O projeto Drone Feeder é o serviço Back-end, que irá fornecer informações aos drones, monitorando as entregas realizadas.

O Drone Feeder foi desenvolvido com Java utilizando Spring-boot, para coletar informações como os dados do drone, entregas realizadas e vídeos feitos durante as entregas e adicioná-las em um banco de dados MySQL.

## :computer: Tecnologias utilizadas 

<div align="center">
<img src="https://camo.githubusercontent.com/771cc18a712bf9edb0925a86164c34b0d803c4d9177dd4467eff7b777109c723/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a6176612d4544384230303f7374796c653d666f722d7468652d6261646765266c6f676f3d6a617661266c6f676f436f6c6f723d7768697465" />
<img src="https://camo.githubusercontent.com/4bde567a4772f994f22418e4505a1ac8dc6e6219100251aa79b7279e02c8bb07/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f537072696e672d3644423333463f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67266c6f676f436f6c6f723d7768697465" />
<img src="https://camo.githubusercontent.com/770dc0d5bff8e3849434d5559707f1f515ba5c33b4f42f654aaa67889cb00d90/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f48696265726e6174652d3539363636433f7374796c653d666f722d7468652d6261646765266c6f676f3d48696265726e617465266c6f676f436f6c6f723d7768697465" />
<img src="https://camo.githubusercontent.com/9588f2ce229c0d45ecf35b26f2232857fb601191b6bb6547022acfc8cb6c39ec/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a756e6974352d3235413136323f7374796c653d666f722d7468652d6261646765266c6f676f3d6a756e697435266c6f676f436f6c6f723d7768697465"  />
<img src="https://camo.githubusercontent.com/a4a4a017a5d519d7c4ce2a3cd3d2194fb7af4b1ca424850784565007c2acc7d8/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4d7953514c2d3030354338343f7374796c653d666f722d7468652d6261646765266c6f676f3d6d7973716c266c6f676f436f6c6f723d7768697465" />
<img src="https://camo.githubusercontent.com/506742bb72188756810aa73bf2b2032849399620bf5af3816b68090f24d81a7c/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6170616368655f6d6176656e2d4337314133363f7374796c653d666f722d7468652d6261646765266c6f676f3d6170616368656d6176656e266c6f676f436f6c6f723d7768697465" />
<img src="https://camo.githubusercontent.com/63350538fde994bc287ccd4908809301e157980e6564bf78d2c5cec22c0a5914/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f446f636b65722d3243413545303f7374796c653d666f722d7468652d6261646765266c6f676f3d646f636b6572266c6f676f436f6c6f723d7768697465" />                          
</div>

# :rotating_light: Como utilizar
## :round_pushpin: Pré-requisitos :memo:
- `Apache Maven 3.6.3`
- `Java 11.0.18`
- `docker version 23.0.2`
- `docker-compose version 2.4.1`
- `MySQL version 5.7`


## Instalação do projeto localmente :computer:

- Clone o repositório
 `git@github.com:isabelarfloriano/drone-feeder.git`.
 - Vá para a pasta do projeto
 `cd drone-feeder`.
 - Instale as dependências
 `mvn install`.
 - Configure o arquivo application.yml, localizado no caminho (src/main/resources), alterando os campos username e password com as suas informações locais do MySQL.

## :pushpin: Para executar o Docker
-   Verifique se as portas 3306 e 8080 estão disponíveis.
-   Renomeie o arquivo '.env.example' para '.env' que se encontra na pasta Docker.
-   Abra um terminal na pasta Docker e execute o comando `docker-compose up -d`.
-   Abra outro terminal no mesmo endereço e execute o comando `docker exec -it docker-db-1 mysql -u root -p`, inserindo a senha 'password' quando solicitado. Deve ser exibido normalmente o terminal interativo do MySQL no container.
-   Abra outro terminal no mesmo endereço e execute o comando `docker compose logs -f drone-feeder`. Deve ser exibido no terminal o build da aplicação, assim como quando usamos o comando `mvn spring-boot:run`.

## :pushpin: Para testar o projeto
-   Rode o comando
`mvn test`

## :triangular_flag_on_post: Endpoints
#### :flying_saucer: Drones
```
GET /dronefeeder/drone: lista todos os drones cadastrados
GET /dronefeeder/drone/{id}: busca um drone pelo seu ID
GET /dronefeeder/drone/{id}/deliveries: busca todas as entregas do drone pelo seu ID
GET /dronefeeder/drone/{id}/videos: busca todos os videos do drone pelo seu ID
POST /dronefeeder/drone: cria um novo drone
PUT /dronefeeder/drone/{id}: atualiza um drone existente pelo ID
DELETE /dronefeeder/drone/{id}: exclui um drone existente pelo ID 
```

#### :package: Entregas
```
GET /dronefeeder/delivery: lista todas as entregas cadastradas
GET /dronefeeder/delivery/{id}: busca uma entrega pelo seu ID
POST /dronefeeder/delivery: cria uma nova entrega associada a um drone
PUT /dronefeeder/delivery/{id}: atualiza uma entrega existente pelo seu ID
DELETE /dronefeeder/delivery/{id}: exclui uma entrega existente pelo seu ID
```
#### :movie_camera: Vídeos
```
GET /dronefeeder/video: lista todos os vídeos cadastrados
GET /dronefeeder/video/{id}: busca um video pelo seu ID
POST /dronefeeder/video: cria um novo vídeo associado a um drone
PUT /dronefeeder/video/{id}: atualiza um vídeo existente pelo seu ID
DELETE /dronefeeder/video/{id}: exclui um vídeo existente pelo seu ID
```
# :rotating_light: Documentação
## :large_orange_diamond: Detalhamento dos endpoints dos Drones
<br/>

### **Lista todos os drones** 
##### `GET` /dronefeeder/drone

  <br/>

  Esse endpoint retorna status ``200`` e o todos os Drones cadastrados.

  - Exemplo `response body`
    ```json
        [
            {
                "id": 1,
                "marca": "marca1",
                "modelo": "modelo2"
            },
            {
                "id": 2,
                "marca": "marca2",
                "modelo": "modelo2"
            },
            {
                "id": 3,
                "marca": "marca3",
                "modelo": "modelo3"
            }
        ]
    ```
  <br/>


### **Lista o drone pelo id** 
##### `GET` /dronefeeder/drone/:id

  <br/>

  Esse endpoint retorna status ``200`` e o Drone cadastrado.

  - Exemplo `response body`
    ```json
        {
            "id": 1,
            "marca": "marca1",
            "modelo": "modelo2"
        }
    ```
  <br/>


### **Cria um novo drone** 
##### `POST` /dronefeeder/drone

  <br/>

  Esse endpoint retorna status ``200`` e o Drone cadastrado.

  - Exemplo `request body` 
    ``` json
        {
            "marca": "marca",
            "modelo": "modelo"
        }
    ```

  - Exemplo `response body`
    ```json
        {
            "id": 1,
            "marca": "marca",
            "modelo": "modelo"
        }
    ```
  <br/>


### **Atualiza um drone pelo id** 
##### `PUT` /dronefeeder/drone/:id

  <br/>

  Esse endpoint retorna status ``200`` e atualiza o Drone cadastrado.

  - Exemplo `request body` 
    ``` json
        {
            "marca": "marca",
            "modelo": "modelo"
        }
    ```

  - Exemplo `response body`
    ```json
        {
            "id": 1,
            "marca": "marca1",
            "modelo": "modelo2"
        }
    ```
  <br/>


### **Deleta um drone pelo id** 
##### `DELETE` /dronefeeder/drone/:id

  <br/>

  Esse endpoint retorna status ``200``

  <br/>

## :large_orange_diamond: Detalhamento dos endpoints das Deliveries
### **Lista todos os Deliverys** 
##### `GET` /dronefeeder/delivery

  <br/>

  Esse endpoint retorna status ``200`` e o todos os Deliverys cadastrados.

  - Exemplo `response body`
    ```json
            [
                {
                    "id": 2,
                    "latitude": "15.7975° S",
                    "longitude": "47.8919° W",
                    "dateAndTime": "2022-08-04T18:09:16.946+00:00",
                    "deliveryStatus": true,
                    "deliveryDateAndTime": "2022-08-04T18:53:44.599+00:00",
                    "drone": {
                        "id": 22,
                        "marca": "Marca22",
                        "modelo": "Modelo22"
                    },
                    "video": {
                        "id": 25,
                        "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
                        "drone": {
                            "id": 22,
                            "marca": "Marca22",
                            "modelo": "Modelo22"
                        }
                    }
                },
                {
                    "id": 28,
                    "latitude": "15.7975° S",
                    "longitude": "47.8919° W",
                    "dateAndTime": "2022-08-04T18:54:15.250+00:00",
                    "deliveryStatus": false,
                    "deliveryDateAndTime": null,
                    "drone": {
                        "id": 23,
                        "marca": "Marca22",
                        "modelo": "Modelo22"
                    },
                    "video": {
                        "id": 27,
                        "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
                        "drone": {
                            "id": 23,
                            "marca": "Marca22",
                            "modelo": "Modelo22"
                        }
                    }
                }
            ]
    ```
  <br/>


### **Lista o Delivery pelo id** 
##### `GET` /dronefeeder/delivery/:id

  <br/>

  Esse endpoint retorna status ``200`` e o Delivery cadastrado.

  - Exemplo `response body`
    ```json
            {
                "id": 2,
                "latitude": "15.7975° S",
                "longitude": "47.8919° W",
                "dateAndTime": "2022-08-04T18:09:16.946+00:00",
                "deliveryStatus": true,
                "deliveryDateAndTime": "2022-08-04T18:53:44.599+00:00",
                "drone": {
                    "id": 22,
                    "marca": "Marca22",
                    "modelo": "Modelo22"
                },
                "video": {
                    "id": 25,
                    "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
                    "drone": {
                        "id": 22,
                        "marca": "Marca22",
                        "modelo": "Modelo22"
                    }
                }
            }
    ```
  <br/>


### **Cria um novo Delivery** 
##### `POST` /dronefeeder/delivery

  <br/>

  Esse endpoint retorna status ``200`` e o Delivery cadastrado.

  - Exemplo `request body` 
    ``` json
        {
                "latitude": "15.7975° S",
                "longitude": "47.8919° W",
                "drone": "1"
        }
    ```

  - Exemplo `response body`
    ```json
        {
            "id": 2,
            "latitude": "15.7975° S",
            "longitude": "47.8919° W",
            "dateAndTime": "2022-08-04T18:09:16.946+00:00",
            "deliveryStatus": true,
            "deliveryDateAndTime": "2022-08-04T18:53:44.599+00:00",
            "drone": {
                "id": 22,
                "marca": "Marca22",
                "modelo": "Modelo22"
            },
            "video": {
                "id": 25,
                "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
                "drone": {
                    "id": 22,
                    "marca": "Marca22",
                    "modelo": "Modelo22"
                }
            }
        }
    ```
  <br/>


### **Atualiza um delivery pelo id** 
##### `PUT` /dronefeeder/delivery/:id

  <br/>

  Esse endpoint retorna status ``200`` e atualiza o Delivery cadastrado.

  - Exemplo `request body` 
    ``` json
        {
                "latitude": "15.7975° S",
                "longitude": "47.8919° W",
                "drone": "1"
        }
    ```

  - Exemplo `response body`
    ```json
        {
            "id": 2,
            "latitude": "15.7975° S",
            "longitude": "47.8919° W",
            "dateAndTime": "2022-08-04T18:09:16.946+00:00",
            "deliveryStatus": true,
            "deliveryDateAndTime": "2022-08-04T18:53:44.599+00:00",
            "drone": {
                "id": 22,
                "marca": "Marca22",
                "modelo": "Modelo22"
            },
            "video": {
                "id": 25,
                "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
                "drone": {
                    "id": 22,
                    "marca": "Marca22",
                    "modelo": "Modelo22"
                }
            }
        }
    ```
  <br/>


### **Finaliza um delivery pelo id** 
##### `PATCH` /dronefeeder/delivery/:id/finish

  <br/>

  Esse endpoint retorna status ``200`` e finaliza o Delivery cadastrado.

  - Exemplo `response body`
    ```json
        {
            "id": 2,
            "latitude": "15.7975° S",
            "longitude": "47.8919° W",
            "dateAndTime": "2022-08-04T18:09:16.946+00:00",
            "deliveryStatus": true,
            "deliveryDateAndTime": "2022-08-04T18:53:44.599+00:00",
            "drone": {
                "id": 22,
                "marca": "Marca22",
                "modelo": "Modelo22"
            },
            "video": {
                "id": 25,
                "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
                "drone": {
                    "id": 22,
                    "marca": "Marca22",
                    "modelo": "Modelo22"
                }
            }
        }
    ```
  <br/>


### **Deleta um Delivery pelo id** 
##### `DELETE` /dronefeeder/delivery/:id

  <br/>

  Esse endpoint retorna status ``200``

  <br/>

## :large_orange_diamond: Detalhamento dos endpoints dos Vídeos
### **Lista todos os videos** 
##### `GET` /dronefeeder/video

  <br/>

  Esse endpoint retorna status ``200`` e o todos os Videos cadastrados.

  - Exemplo `response body`
    ```json
        [
            {
                "id": 1,
                "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
                "drone": {
                    "id": 1,
                    "marca": "modelo1",
                    "modelo": "marca1"
                }
            },
            {
                "id": 2,
                "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
                "drone": {
                    "id": 2,
                    "marca": "modelo2",
                    "modelo": "marca2"
                }
            },
            {
                "id": 3,
                "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
                "drone": {
                    "id": 3,
                    "marca": "modelo3",
                    "modelo": "marca3"
                }
            }
        ]
    ```
  <br/>


### **Lista o video pelo id** 
##### `GET` /dronefeeder/video/:id

  <br/>

  Esse endpoint retorna status ``200`` e o Video cadastrado.

  - Exemplo `response body`
    ```json
        {
            "id": 3,
            "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
            "drone": {
                "id": 3,
                "marca": "modelo3",
                "modelo": "marca3"
            }
        }
    ```
  <br/>


### **Cria um novo Video** 
##### `POST` /dronefeeder/video

  <br/>

  Esse endpoint retorna status ``200`` e o Video cadastrado.

  - Exemplo `request body` 
    ``` json
            {
                "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
                "drone": 3
            }
    ```

  - Exemplo `response body`
    ```json
        {
            "id": 3,
            "url": "https://www.youtube.com/watch?v=qcsszdkjlXg",
            "drone": {
                "id": 3,
                "marca": "modelo3",
                "modelo": "marca3"
            }
        }
    ```
  <br/>




## :busts_in_silhouette: Autores :bust_in_silhouette:
- [Isabela Rozani Floriano](https://github.com/isabelarfloriano)
- [Lucas Castanheira](https://github.com/Lucas-PCN)
- [Vanessa Bastos](https://github.com/vanessabastos)

