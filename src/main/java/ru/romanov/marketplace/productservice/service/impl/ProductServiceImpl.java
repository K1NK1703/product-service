package ru.romanov.marketplace.productservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.romanov.marketplace.productservice.dto.ProductFilter;
import ru.romanov.marketplace.productservice.service.ProductService;
import ru.romanov.marketplace.productservice.entity.product.Product;
import ru.romanov.marketplace.productservice.repository.ProductRepository;
import ru.romanov.marketplace.productservice.service.ProductSearchService;
import ru.romanov.marketplace.productservice.entity.product.ProductCategory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    static String PRODUCT_NOT_FOUND_TEMPLATE = "Product with id %s not found";

    ProductRepository productRepository;
    ProductSearchService productSearchService;
    Path uploadDir = Paths.get("uploads");

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByCategory(ProductCategory category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateProduct(Long productId, Product updated) {
        Product product = findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(PRODUCT_NOT_FOUND_TEMPLATE, productId)));
        product.setName(updated.getName());
        product.setDescription(updated.getDescription());
        product.setPrice(updated.getPrice());
        product.setDiscount(updated.getDiscount());

        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(PRODUCT_NOT_FOUND_TEMPLATE, id));
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<String> uploadPhotos(Long productId, List<MultipartFile> files) throws IOException {
        Product product = findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(PRODUCT_NOT_FOUND_TEMPLATE, productId)));
        Files.createDirectories(uploadDir);

        for (MultipartFile file : files) {
            String fileName = String.format("%s_%s", UUID.randomUUID(), file.getOriginalFilename());
            Path filePath = uploadDir.resolve(fileName);
            Files.write(filePath, file.getBytes());
            String fileUrl = String.format("/uploads/%s", fileName);
            product.getPhotos().add(fileUrl);
        }

        productRepository.save(product);
        return product.getPhotos();
    }

    @Override
    public List<Product> searchProducts(ProductFilter filter) {
        return productSearchService.search(filter);
    }
}
