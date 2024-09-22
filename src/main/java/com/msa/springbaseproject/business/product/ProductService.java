package com.msa.springbaseproject.business.product;

import com.msa.springbaseproject.business.product.model.entity.Product;
import com.msa.springbaseproject.business.product.model.request.ProductRequest;
import com.msa.springbaseproject.common.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product,ProductRepository> {

    public ProductService(ProductRepository repository) {
        super(repository);
    }

    @Transactional
    public void save(ProductRequest request) {
        Product product = new Product();
        product.setProductName(request.getName());
        repository.save(product);
    }
}
