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
    \/ 5. Логирование в сервисах (AOP, logbook);
    \/ 6. Подключить Actuator
    7. Интеграционные тесты
    8. Сборка в Docker образ
    \/ 9. При запуске приложения рандомный порт
    10. Генерация на основе OpenAPI (уточнить, что имел в виду) (?)

    2, 3 - только для сущности Employee
*/
