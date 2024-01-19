Hotel Reservation System
Этот проект представляет собой систему бронирования для отеля, разработанную с использованием Java Spring, базы данных Postgres и механизма безопасности Spring Security. Проект предоставляет REST-сервис для общения по протоколу HTTP и поддерживает две основные роли - пользователь и администратор.

Требования
Java 8+
Maven
PostgreSQL
Установка и настройка
Клонирование репозитория:

git clone https://github.com/yourusername/hotel-reservation-system.git
cd hotel-reservation-system
Настройка базы данных:

Создайте базу данных PostgreSQL с именем hotel_reservation.
В файле src/main/resources/application.properties укажите настройки вашей базы данных (название, пользователь, пароль).
Запуск приложения:


mvn spring-boot:run
Приложение будет доступно по адресу http://localhost:8080.

Роли и права доступа
Роли:
USER: Пользователь, который может бронировать и отменять бронирование.
ADMIN: Администратор, который может добавлять и удалять номера, а также редактировать бронирования.

API Endpoints
Пользовательские операции:
Бронирование номера:

POST /api/reservations
Отмена бронирования:

DELETE /api/reservations/{reservationId}
Операции администратора:
Добавление номера:

POST /api/rooms
Удаление номера:

DELETE /api/rooms/{roomId}
Редактирование бронирования:

PUT /api/reservations/{reservationId}
Аутентификация и авторизация
Для выполнения операций требуется аутентификация. Для этого используется Spring Security.


Форма аутентификации:
Endpoint:

POST /api/authenticate
Запрос:

{
    "username": "your_username",
    "password": "your_password"
}
Ответ:

{
    "token": "your_access_token"
}

В каждом запросе к защищенным ресурсам в заголовке Authorization должен быть передан токен доступа.

Пример:

Authorization: Bearer your_access_token
Примеры использования

Бронирование номера:

POST /api/reservations
Authorization: Bearer your_access_token

{
    "roomId": 123,
    "checkInDate": "2024-01-20",
    "checkOutDate": "2024-01-25"
}
Отмена бронирования:

DELETE /api/reservations/456
Authorization: Bearer your_access_token
Добавление номера:

POST /api/rooms
Authorization: Bearer your_access_token

{
    "number": "101",
    "type": "Standard",
    "price": 100.0
}

Удаление номера:
DELETE /api/rooms/123
Authorization: Bearer your_access_token

Редактирование бронирования:
PUT /api/reservations/789
Authorization: Bearer your_access_token

{
    "checkInDate": "2024-01-22",
    "checkOutDate": "2024-01-27"
}
