# registro-backEnd-java
Prueba de BackEnd

Api para consultar clientes registrados a cierto beneficio. Para llevar a cabo el siguiente prueba se usó Spring boot, java 17
## Table of Contents

- [Installation](#installation)
- [Usage](#usage)

## Installation
1. Java 17
2. Vs Code
3. mvn clean
4. mvn install
5. Run
## Usage

Once the application is running, you can use Postman:

- `http://localhost:8080/customer/save`: Save only customer and return message of congratulation
```
    BodyRequest:
    {
        "name": "Jose Cruz",
        "email": "jose@gmail.com",
        "phone": "593990800276"
    }
```

- `http://localhost:8080/customer/getAll`: Return a list of customer
- `http://localhost:8080/campaign/save`: Save campaign and if no exist customer save too and return message of congratulation
```
BodyRequest:
    {
        "name": "Jose Cruz",
        "email": "jose@gmail.com",
        "phone": "593990800276",
        "groupBenefit": "SK"
    }
```
- `http://localhost:8080/campaign/getAll`: Return a list of campaign


