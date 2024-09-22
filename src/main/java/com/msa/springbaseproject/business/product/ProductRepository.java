package com.msa.springbaseproject.business.product;

import com.msa.springbaseproject.business.product.model.entity.Product;
import com.msa.springbaseproject.common.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product> {
}
