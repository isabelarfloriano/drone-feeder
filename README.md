#  Seja bem vindo ao repositório Projeto Drone-Feeder 
<div align="center">
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpL8tgwBXKIDLVDhX7HQe_shkDCnoZh-sH-g&usqp=CAU" width="300px" />
</div>

## Descrição do Projeto :package:

Projeto desenvolvido para monitorar entregas realizadas por drones.
Neste projeto foi construido o back-end com Java utilizando Spring-boot, para coletar informações como os dados do drone, entregas realizadas e vídeos feitos durante as entregas e adiciona-las no banco de dados MySQL.

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


## Instalação do projeto localmente :computer:

- Clone o repositório
 `git@github.com:isabelarfloriano/drone-feeder.git`
 - Vá para a pasta do projeto
 `cd drone-feeder`
 - Instale as dependências
 `mvn install` 

## :pushpin: Para executar o Docker
-   Verifique se as portas 3306 e 8080 estão disponíveis.
-   Realize o 'git pull' na branch 'docker-plus-mysql'. Link do repositório do  [projeto](https://github.com/isabelarfloriano/drone-feeder/tree/docker-plus-mysql).
-   Renomeie o arquivo '.env.example' para '.env' que se encontra no arquivo Docker.
-   Abra um terminal na pasta Docker e execute o comando 'docker-compose up -d'.
-   Abra outro terminal no mesmo endereço e execute o comando 'docker exec -it docker-db-1 mysql -u root -p', inserindo a senha 'password' quando solicitado. Deve ser exibido normalmente o terminal interativo do MySQL no container.
-   Abra outro terminal no mesmo endereço e execute o comando 'docker compose logs -f drone-feeder'. Deve ser exibido no terminal o build da aplicação, assim como quando usamos o comando 'mvn spring-boot:run'.

## :large_orange_diamond: Para realizar testes de requisição no Insomnia, Postman, etc...
Utilize o objeto abaixo como modelo na rota 'localhost:8080/dronefeeder/drone':  
{  
"brand": "Deerc",  
"modelName": "D20",  
"serialNumber": "654321"  
}

## :busts_in_silhouette: Autores :bust_in_silhouette:
- [Isabela Rozani Floriano](https://github.com/isabelarfloriano)
- [Lucas Castanheira](https://github.com/Lucas-PCN)
- [Vanessa Bastos](https://github.com/vanessabastos)

