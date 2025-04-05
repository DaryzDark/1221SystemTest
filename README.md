# 🥗 Calorie Tracker API

**Calorie Tracker** — это REST API-сервис для учёта пользователей, блюд и приёмов пищи с автоматическим расчётом дневной нормы калорий.

---

## 🚀 Возможности

- 👤 Регистрация пользователей и расчёт нормы калорий по формуле Харриса-Бенедикта
- 🍲 Добавление блюд с макроэлементами
- 📅 Учет приёмов пищи с количеством порций
- 📊 Ежедневный отчёт и история потребления калорий
- ✅ Проверка, уложился ли пользователь в свою норму

---

## 🐳 Запуск с помощью Docker Compose

> Приложение использует PostgreSQL, Spring Boot и Liquibase.  
> Убедись, что у тебя установлен [Docker](https://www.docker.com/) и [Docker Compose](https://docs.docker.com/compose/).

---

### 🔧 Шаг 1. Собери jar-файл приложения

```bash
./gradlew clean build
```

> Убедись, что в `build/libs/` появился файл `.jar`.

---

### 🐳 Шаг 2. Построй Docker-образ приложения

```bash
docker build -t calorie-tracker-app:latest .
```

---

### 🧩 Шаг 3. Запусти контейнеры с `docker-compose`

```bash
docker-compose up -d
```

📌 Это запустит:

- PostgreSQL (порт: `5432`)
- Приложение Calorie Tracker (порт: `8080`)

---

## 🛠 Настройки по умолчанию

Переменные окружения уже заданы в `docker-compose.yml`:

```yaml
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/db
SPRING_DATASOURCE_USERNAME=admin
SPRING_DATASOURCE_PASSWORD=admin
```

---

## 🔍 Эндпоинты API

| Метод | URL | Описание |
|-------|-----|----------|
| `POST` | `/api/users` | Создать пользователя |
| `POST` | `/api/dishes` | Добавить блюдо |
| `POST` | `/api/meals` | Добавить приём пищи |
| `GET` | `/api/reports/daily` | Ежедневный отчёт |
| `GET` | `/api/reports/check` | Проверка нормы |
| `GET` | `/api/reports/history` | История питания |

---

## 🧪 Postman

Postman-коллекция:  
📁 calorie-tracker.postman_collection.json

---

## 🛑 Остановка контейнеров

```bash
docker-compose down
```

---
