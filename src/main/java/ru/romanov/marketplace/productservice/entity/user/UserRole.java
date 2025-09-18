package ru.romanov.marketplace.productservice.entity.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("Администратор"),
    USER("Пользователь"),
    COURIER("Курьер"),
    SELLER("Продавец");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
