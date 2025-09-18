package ru.romanov.marketplace.productservice.service;

import org.springframework.web.multipart.MultipartFile;
import ru.romanov.marketplace.productservice.dto.ProductFilter;
import ru.romanov.marketplace.productservice.entity.product.Product;
import ru.romanov.marketplace.productservice.entity.product.ProductCategory;

import java.util.List;
import java.util.Optional;
import java.io.IOException;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findByCategory(ProductCategory category);

    Product updateProduct(Long productId, Product updated);

    void deleteById(Long id);

    List<String> uploadPhotos(Long productId, List<MultipartFile> files) throws IOException;

    List<Product> searchProducts(ProductFilter filter);

}
