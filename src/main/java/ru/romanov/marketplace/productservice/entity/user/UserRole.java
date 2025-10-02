package ru.romanov.marketplace.productservice.entity.user;

import lombok.Getter;

@Getter
public enum UserRole {
    EMPLOYEE("Работник ПВЗ");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
