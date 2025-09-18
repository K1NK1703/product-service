package ru.romanov.marketplace.productservice.service;

import ru.romanov.marketplace.productservice.dto.ProductFilter;
import ru.romanov.marketplace.productservice.entity.product.Product;

import java.util.List;

public interface ProductSearchService {

    List<Product> search(ProductFilter filter);

}
