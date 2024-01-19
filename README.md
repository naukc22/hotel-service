# Hotel Reservation System

Этот проект представляет собой систему бронирования для отеля, разработанную с использованием Java Spring, базы данных Postgres и механизма безопасности Spring Security. Проект предоставляет REST-сервис для общения по протоколу HTTP и поддерживает две основные роли - пользователь и администратор.

## Требования

- Java 8+
- Maven
- PostgreSQL

## Установка и настройка

1. **Клонирование репозитория:**
    ```bash
    git clone https://github.com/yourusername/hotel-reservation-system.git
    cd hotel-reservation-system
    ```

2. **Настройка базы данных:**
    - Создайте базу данных PostgreSQL с именем `hotel_reservation`.
    - В файле `src/main/resources/application.properties` укажите настройки вашей базы данных (название, пользователь, пароль).

3. **Запуск приложения:**
    ```bash
    mvn spring-boot:run
    ```

Приложение будет доступно по адресу `http://localhost:8080`.

## Роли и права доступа

### Роли:

- **USER:** Пользователь, который может бронировать и отменять бронирование.
- **ADMIN:** Администратор, который может добавлять и удалять номера, а также редактировать бронирования.

## API Endpoints

### Пользовательские операции:

- **Бронирование номера:**
    ```http
    POST /api/reservations
    ```
  
- **Отмена бронирования:**
    ```http
    DELETE /api/reservations/{reservationId}
    ```

### Операции администратора:

- **Добавление номера:**
    ```http
    POST /api/rooms
    ```

- **Удаление номера:**
    ```http
    DELETE /api/rooms/{roomId}
    ```

- **Редактирование бронирования:**
    ```http
    PUT /api/reservations/{reservationId}
    ```

## Аутентификация и авторизация

Для выполнения операций требуется аутентификация. Для этого используется Spring Security.

### Форма аутентификации:

- **Endpoint:**
    ```http
    POST /api/authenticate
    ```

- **Запрос:**
    ```json
    {
        "username": "your_username",
        "password": "your_password"
    }
    ```

- **Ответ:**
    ```json
    {
        "token": "your_access_token"
    }
    ```

В каждом запросе к защищенным ресурсам в заголовке `Authorization` должен быть передан токен доступа.

Пример:
```http
Authorization: Bearer your_access_token
