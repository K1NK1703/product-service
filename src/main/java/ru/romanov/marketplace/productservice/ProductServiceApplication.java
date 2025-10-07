package ru.romanov.marketplace.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}

/* TODO:
    \/ 1. Репозиторий для Employee с помощью Jooq;
    \/ 2. Сервисы;
    \/ 3. Контроллеры, ProductExceptionHandler;
    \/ 4. Префиксы для endpoint'ов;
    \/ 5. Логирование в сервисах (logbook);
    \/ 6. Подключить Actuator
    7. Интеграционные тесты
    \/ 8. Сборка в Docker образ (написать Dockerfile, добавить его в Docker-compose)
    \/ 9. При запуске приложения рандомный порт
    \/ 10. Генерация на основе OpenAPI (описать все сервисы, вручную написать openapi.yml, сгенерировать контроллеры)
    \/ 11. Delete-методы
    \/ 12. Нейминг
*/
