package com.msa.springbaseproject.business.product;

import com.msa.springbaseproject.business.product.model.ProductMapper;
import com.msa.springbaseproject.business.product.model.entity.Product;
import com.msa.springbaseproject.business.product.model.request.ProductRequest;
import com.msa.springbaseproject.business.product.model.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> response = productService.findAll().stream().map(ProductMapper::mapToResponse).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.mapToResponse(product));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request) {
        productService.save(request);
        return ResponseEntity.status(HttpStatus.OK).body("Create successfully");
    }
}
