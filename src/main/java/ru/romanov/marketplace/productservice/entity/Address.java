package ru.romanov.marketplace.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class Address {

    @Column(nullable = false)
    String country;

    @Column(nullable = false)
    String region;

    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    String street;

    @Column(name = "house_number", nullable = false)
    String houseNumber;

}
