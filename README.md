# Product Service

### Описание

Учебный Java-проект по управлению пунктами выдачи, их сотрудниками и контактной информацией.

В проекте есть 3 основные сущности: **PickupPoint** (Пункт выдачи), **Employee** extends BaseUser (Сотрудник ПВЗ. Есть возможность создавать и других пользователей) и **PointContact** (Контактная информация о ПВЗ).

### Связи между сущностями

PickupPoint --OneToMany--> Employee

PickupPoint --ManyToOne--> PointContact

## Функциональность

- **Управление сотрудниками** - CRUD операции, фильтрация
- **Управление пунктами выдачи** - создание, поиск, обновление
- **Управление контактами** - телефон, сайт, связь с пунктами выдачи
- **jOOQ** - типобезопасные SQL запросы

## Стек технологий

- Java 21
- Spring Boot 3.5.6
- Gradle 9.1.0
- PostgreSQL 17.5
- Flyway 11.12.0
- Jooq 3.20.0
- Docker & Docker Compose
- Mapstruct 1.6.3
- Logbook 3.12.3
- Micrometer 1.5.3
- Actuator
- OpenAPI-Generator 7.16.0 & Swagger UI 2.8.13

## Архитектура

Гексагональная архитектура

<table style="width:100%; border-collapse: collapse; border: 1px solid #ddd;">
  <tr>
    <td style="border: 1px solid #ddd; padding: 10px; text-align: center;">API Layer (REST)</td>
  </tr>
  <tr>
    <td style="border: 1px solid #ddd; padding: 10px; text-align: center;">Business Logic (Service)</td>
  </tr>
  <tr>
    <td style="border: 1px solid #ddd; padding: 10px; text-align: center;">Data Access (jOOQ Repositories)</td>
  </tr>
  <tr>
    <td style="border: 1px solid #ddd; padding: 10px; text-align: center;">PostgreSQL + Flyway</td>
  </tr>
</table>

## Запуск

1. **Клонировать** - git clone https://github.com/K1NK1703/product-service.git
2. **Сбилдить** - ./gradlew clean build
3. **Собрать Docker-образ** - docker-compose build --no-cache
4. **Поднять контейнеры** - docker-compose up -d

После успешного запуска API будет доступно по адресу - http://localhost:${DOCKER_PORT}/api

Подключение к БД будет доступно с hostname=localhost и на порту {DOCKER_PORT}.

_{DOCKER_PORT} - это значение порта, которое Docker выберет автоматически. Чтобы его узнать, достаточно ввести команду **docker ps**_

## API Endpoint's

Swagger UI - http://localhost:${DOCKER_PORT}/v3/api-docs

Actuator endpoints - http://localhost:${DOCKER_PORT}/actuator

Например, для Employee:
- POST /employees/create - создать сотрудника
- GET /employees/find/{id} - найти по ID
- GET /employees/filter - фильтр сотрудников
- PUT /employees/update - обновить сотрудника
- DELETE /employees/delete/{id} - удалить сотрудника

## Улучшения и идеи

1. Переписать с использованием OpenAPI контракта (*)
2. Добавить интеграционные тесты

(*) _В папке проекта **/openapi** лежит описанный контракт, по которому OpenAPI-Generator генерирует необходимые DTO и интерфейсы для реализации контроллеров._
