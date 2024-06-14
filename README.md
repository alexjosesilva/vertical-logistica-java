[![Latest Version](https://img.shields.io/github/v/release/alexjosesilva/vertical-logistica-java?include_prereleases)](https://github.com/alexjosesilva/vertical-logistica-java/releases/tag/1.0)
[![License](https://img.shields.io/github/license/alexjosesilva/vertical-logistica-java)]([https://github.com/seu-usuario/seu-repositorio/blob/master/LICENSE](https://github.com/alexjosesilva/vertical-logistica-java/blob/master/LICENSE))
[![codecov](https://codecov.io/gh/alexjosesilva/vertical_logistica_apachecamel/graph/badge.svg?token=GUWHI4VKML)](https://codecov.io/gh/alexjosesilva/vertical-logistica-java)

# Vertical Logística

Este projeto é uma API REST desenvolvida em Java 21 com Spring Boot para processar arquivos de pedidos de um sistema legado, transformando-os em JSON normalizado. O objetivo é integrar dois sistemas, recebendo um arquivo via API REST, processando-o e retornando os dados em um formato estruturado.

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Maven
- JaCoCo para cobertura de testes
- Codecov para integração contínua de cobertura de testes

## Como Executar

1. Clone o repositório.
2. Navegue até o diretório do projeto.
3. Execute o comando `mvn spring-boot:run`.

## Endpoints

- `POST /api/orders/upload`: Faz o upload do arquivo de pedidos e retorna os dados processados em formato JSON.

## Estrutura do Projeto

- **controller**: Contém os controladores REST.
- **service**: Contém a lógica de negócios.
- **model**: Contém as classes de modelo (entidades).
- **repository**: Contém as interfaces de repositório.(opcional).
- **exception**: Contém as classes de exceção personalizada.

```
src
└── main
├── java
│   └── com
│       └── vertical
│           └── logistica
│               ├── controller
│               │   └── OrderController.java
│               ├── service
│               │   └── OrderService.java
│               ├── model
│               │   └── Order.java
│               │   └── User.java
│               │   └── Product.java
│               ├── exception
│               │   └── CustomException.java
│               └── VerticalLogisticaApplication.java
└── resources
└── application.properties
```

## Considerações Finais
Este projeto foi desenvolvido para demonstrar habilidades em desenvolvimento backend, arquitetura orientada a serviços, processamento de arquivos e manipulação de dados. A escolha de tecnologias e padrões arquiteturais visa simplicar e eficiência no projeto.
