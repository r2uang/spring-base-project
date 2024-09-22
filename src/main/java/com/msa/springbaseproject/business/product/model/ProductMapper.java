package com.msa.springbaseproject.business.product.model;

import com.msa.springbaseproject.business.product.model.entity.Product;
import com.msa.springbaseproject.business.product.model.response.ProductResponse;

public class ProductMapper {

    public static ProductResponse mapToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getProductName());
        return productResponse;
    }
}
