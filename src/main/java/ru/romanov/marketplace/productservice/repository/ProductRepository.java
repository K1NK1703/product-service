package ru.romanov.marketplace.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanov.marketplace.productservice.entity.product.Product;
import ru.romanov.marketplace.productservice.entity.product.ProductCategory;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(ProductCategory category);

}
