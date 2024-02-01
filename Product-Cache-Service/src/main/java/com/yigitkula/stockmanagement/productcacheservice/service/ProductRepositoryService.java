package com.yigitkula.stockmanagement.productcacheservice.service;

import com.yigitkula.stockmanagement.productcacheservice.enums.Language;
import com.yigitkula.stockmanagement.productcacheservice.repository.entity.Product;

public interface ProductRepositoryService {
    Product getProduct(Language language, Long productId);

    void deleteProducts(Language language);
}
