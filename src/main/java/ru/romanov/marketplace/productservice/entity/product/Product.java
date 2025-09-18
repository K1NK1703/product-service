package ru.romanov.marketplace.productservice.entity.product;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(generator = "product_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "product_gen", sequenceName = "products_id_seq", allocationSize = 1)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(length = 2000)
    String description;

    @Column(nullable = false)
    BigDecimal price;

    @Column
    Double discount;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "product_photos", joinColumns = @JoinColumn(name = "product_id"))
    @Column(nullable = false)
    List<String> photos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProductCategory category;

}
