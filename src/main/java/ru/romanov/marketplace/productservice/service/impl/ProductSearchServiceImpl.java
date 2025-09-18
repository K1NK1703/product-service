package ru.romanov.marketplace.productservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.marketplace.productservice.dto.ProductFilter;
import ru.romanov.marketplace.productservice.entity.product.Product;
import ru.romanov.marketplace.productservice.entity.product.QProduct;
import ru.romanov.marketplace.productservice.service.ProductSearchService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductSearchServiceImpl implements ProductSearchService {

    JPAQueryFactory queryFactory;

    @Transactional(readOnly = true)
    public List<Product> search(ProductFilter filter) {

        QProduct product = QProduct.product;
        BooleanBuilder predicate = new BooleanBuilder();

        if (filter.nameContains() != null && !filter.nameContains().isBlank()) {
            predicate.and(product.name.lower().contains(filter.nameContains().toLowerCase()));
        }
        if (filter.minPrice() != null) {
            predicate.and(product.price.goe(filter.minPrice()));
        }
        if (filter.maxPrice() != null) {
            predicate.and(product.price.loe(filter.maxPrice()));
        }
        if (filter.minDiscount() != null) {
            predicate.and(product.discount.goe(filter.minDiscount()));
        }
        if (filter.maxDiscount() != null) {
            predicate.and(product.discount.loe(filter.maxDiscount()));
        }

        return queryFactory
                .selectFrom(product)
                .where(predicate)
                .orderBy(product.name.asc())
                .fetch();
    }
}
