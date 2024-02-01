package com.yigitkula.stockmanagement.productservice.service;

import com.yigitkula.stockmanagement.productservice.enums.Language;
import com.yigitkula.stockmanagement.productservice.repository.entity.Product;
import com.yigitkula.stockmanagement.productservice.request.ProductCreateRequest;
import com.yigitkula.stockmanagement.productservice.request.ProductUpdateRequest;

import java.util.List;

public interface IProductRepositoryService {

    Product createProduct(Language language, ProductCreateRequest productCreateRequest);
    Product getProduct(Language language, Long productId);
    List<Product> getProducts(Language language);
    Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest);
    Product deleteProduct(Language language, Long productId);

}
