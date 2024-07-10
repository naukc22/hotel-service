# Hotel Reservation System

Этот проект представляет собой систему бронирования для отеля, разработанную с использованием Java Spring, базы данных Postgres и механизма безопасности Spring Security. Проект предоставляет REST-сервис для общения по протоколу HTTP и поддерживает две основные роли - пользователь и администратор.

![image](https://github.com/naukc22/hotel-service/assets/134156061/79090a36-9239-45a6-ad73-ba500d9bb720)
![image](https://github.com/naukc22/hotel-service/assets/134156061/0b445664-a9a8-4bf6-a894-f69cd8087de5)
![image](https://github.com/naukc22/hotel-service/assets/134156061/eef4d12e-5cef-4f23-8d07-86a03e6d850e)
![image](https://github.com/naukc22/hotel-service/assets/134156061/89828ae9-a54f-4020-89d8-86f919a61f65)



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
