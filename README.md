# Клиентский API

Этот проект предоставляет API для управления клиентами и их контактной информацией. Он позволяет добавлять, получать и фильтровать клиентов и их контакты.

## Эндпоинты

### 1. Получение списка клиентов
- **URL**: `/clients`
- **Метод**: `GET`
- **Описание**: Возвращает список всех клиентов.
- **Ответ**:
  ```json
  [
    { "id": 1, "name": "John Doe" },
    { "id": 2, "name": "Jane Smith" }
  ]
  ```

### 2. Получение информации о конкретном клиенте
- **URL**: `/clients/{id}`
- **Метод**: `GET`
- **Описание**: Возвращает информацию о клиенте по его идентификатору (ID), включая список его контактов.
- **Параметры**:
    - `id` — ID клиента, передается в URL.
- **Ответ**:
  ```json
  {
    "id": 1,
    "name": "John Doe",
    "contacts": [
      { "type": "PHONE", "value": "123-456-7890" },
      { "type": "EMAIL", "value": "john@example.com" }
    ]
  }
  ```

### 3. Добавление нового клиента
- **URL**: `/clients/add`
- **Метод**: `POST`
- **Описание**: Добавляет нового клиента.
- **Тело запроса**:
  ```json
  { "name": "New Client" }
  ```
- **Ответ**: Перенаправление на список клиентов.

### 4. Добавление нового контакта для клиента
- **URL**: `/clients/{clientId}/contacts`
- **Метод**: `POST`
- **Описание**: Добавляет новый контакт для клиента.
- **Параметры**:
    - `clientId` — ID клиента, передается в URL.
- **Тело запроса**:
  ```json
  { "type": "EMAIL", "value": "new_email@example.com" }
  ```
- **Ответ**: Перенаправление на страницу клиента.

### 5. Фильтрация контактов по типу
- **URL**: `/clients/{clientId}/contacts/filter`
- **Метод**: `GET`
- **Описание**: Фильтрует контакты клиента по типу (например, только телефоны или только email).
- **Параметры**:
    - `clientId` — ID клиента, передается в URL.
    - `type` — тип контакта (`PHONE`, `EMAIL`), передается в строке запроса как `?type=PHONE` или `?type=EMAIL`.
- **Ответ**:
  ```json
  [
    { "type": "PHONE", "value": "123-456-7890" }
  ]
  ```

## Как использовать
1. Запустите приложение.
2. Все запросы должны быть отправлены на адрес вашего приложения, например: `http://localhost:8080/clients`.
3. В приложении есть html разметка, для удобного использования
## Зависимости
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate Validator

## Настройка
В файле `application.properties` укажите настройки для подключения к базе данных PostgreSQL:

```properties
spring.application.name=UnibellTestApp

spring.datasource.url=jdbc:postgresql://localhost:5432/unibellTestdb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver


spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
