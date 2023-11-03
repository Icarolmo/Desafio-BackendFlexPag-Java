# Challenge-BackendJava-FlexPag

Repositório criado com objetivo de usar o desafio Backend da FlexPag disponível no repositório da [FlexPag](https://github.com/jsantos-examples/flexpag-desafio-backend) como objeto de estudo e aprimoramento das ferramentas necessárias e utilizadas no desáfio. Objetivo do desafio consiste em criar uma API para um serviço de agendamento de pagamentos simples em Java utilizando Spring Boot com: Web, JPA DevTools e Lombok, para banco de dados deve ser usado H2.

## Sobre o desafio

A Flexpag, como uma empresa de tecnologia especializada em soluções digitais de pagamento, propoe um desafio que consiste em implementar um serviço de pagamento agendado. Este serviço deve seguir o seguinte fluxo: 

- Quando um agendamento é enviado deve ser registrado como pending e retornado o id;
- O usuário deve conseguir consultar o status do agendamento, podendo ser: pending ou paid;
- Se o pagamento ainda não foi realizado o usuário pode:
  - Excluir o agendamento;
  - Atualizar a data e valor do agendamento;

## Métodos da aplicação e dependências
Como já mencionado, foi determinado para o desafio o uso restrito as seguintes dependências:
- Spring-Boot-starter-web
- Spring-Boot-starter-data-jpa
- Spring-Boot-devtools
- H2 Database
- Lombok
  
Foi implementado na aplicação todos os métodos requisitados além de um método teste para simulação de pagamento e posteriormente a verificação de regras de negócio relacionadas ao status: 

**[GET]** `/scheduling`: Retorna todos os agendamentos do usuário.
- Exemplo de resposta:
```json
[
    {
        "id": "93d80743-1118-4758-8bb7-5fc57788d68d",
        "status": "pending",
        "date": "05/11/2023",
        "amount": 3000
    },
    {
        "id": "581bd649-bc61-4ee2-8462-03b7f91b7feb",
        "status": "pending",
        "date": "07/11/2023",
        "amount": 3500
    },
    {
        "id": "ed6c8c61-98d1-46ab-9a12-159dc69c093a",
        "status": "pending",
        "date": "12/11/2023",
        "amount": 6000
    }
]
```

**[GET]** `/scheduling?id={UUID do agendamento}`: Retorna o agendamento filtrado pelo id.

- Parâmetro da requisição:
    - id=93d80743-1118-4758-8bb7-5fc57788d68d
- Corpo da resposta:
```json
{
    "id": "93d80743-1118-4758-8bb7-5fc57788d68d",
    "status": "pending",
    "date": "05/11/2023",
    "amount": 3000
}
```

**[POST]** `/scheduling`: Recebe corpo da requisição, realiza validações, cria um agendamento e retorna agendamento criado. 

- Corpo da requisição:
```json
{
    "date": "05/11/2023",
    "amount": 3000
}
```
- Corpo da resposta:
```json
{
    "id": "93d80743-1118-4758-8bb7-5fc57788d68d",
    "status": "pending",
    "date": "05/11/2023",
    "amount": 3000
}
```

**[PUT]** `/scheduling/paymenting?id={UUID do agendamento}`: Método criado para realizar testes. Consulta agendamento, realiza validações, altera o status para paid (pago), salva agendamento e retorna o agendamento atualizado.
- Parâmetro na requisição:
  - id=581bd649-bc61-4ee2-8462-03b7f91b7feb
- Corpo da resposta:
```json
{
    "id": "581bd649-bc61-4ee2-8462-03b7f91b7feb",
    "status": "paid",
    "date": "07/11/2023",
    "amount": 3500
}
```

**[PUT]** `/scheduling?id={UUID do agendamento}`: Consulta agendamento, realiza validações, altera data e valor, salva agendamento e retorna o agendamento alterado.
- Corpo de requisição:
  - id=93d80743-1118-4758-8bb7-5fc57788d68d
```json
{
    "date": "06/11/2023",
    "amount": 4300
}
```
- Corpo da resposta:
```json
{
    "id": "93d80743-1118-4758-8bb7-5fc57788d68d",
    "status": "pending",
    "date": "06/11/2023",
    "amount": 4300
}
```

**[DELETE]** `/scheduling?id={UUID do agendamento}`: Consulta agendamento, realiza validações e exclui agendamento.
- Corpo de requisição:
  - id=93d80743-1118-4758-8bb7-5fc57788d68d
- Consulta no banco após exclusão:
```json
[
    {
        "id": "581bd649-bc61-4ee2-8462-03b7f91b7feb",
        "status": "pending",
        "date": "07/11/2023",
        "amount": 3500
    },
    {
        "id": "ed6c8c61-98d1-46ab-9a12-159dc69c093a",
        "status": "pending",
        "date": "12/11/2023",
        "amount": 6000
    }
]
```



    
