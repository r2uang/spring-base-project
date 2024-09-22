package com.msa.springbaseproject.business.product;

import com.msa.springbaseproject.business.product.model.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {

    public static Specification<Product> getByCondition(String productName) {

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("productName"), productName);
    };
}
