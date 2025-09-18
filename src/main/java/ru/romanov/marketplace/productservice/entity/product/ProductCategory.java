package ru.romanov.marketplace.productservice.entity.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductCategory {
    ELECTRONICS("Электроника"),
    CLOTHING("Одежда"),
    BOOKS("Книги"),
    HOME_APPLIANCES("Бытовая техника"),
    BEAUTY("Красота и здоровье"),
    TOYS("Игрушки"),
    FOOD("Продукты питания"),
    SPORTS("Спорт и отдых"),
    OTHER("Прочее");

    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }
}
