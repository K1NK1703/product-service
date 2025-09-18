package ru.romanov.marketplace.productservice.entity.delivery;

import lombok.Getter;

@Getter
public enum DeliveryStatus {
    CREATED("Создан"),
    AWAITING_DELIVERY("Передаётся в службу доставки"),
    IN_TRANSIT("В пути"),
    AT_PICKUP_POINT("В пункте выдачи"),
    OUT_FOR_DELIVERY("Курьер в пути"),
    DELIVERED("Доставлен"),
    CANCELLED("Отменён");

    private final String status;

    DeliveryStatus(String status) {
        this.status = status;
    }

}
