package com.yigitkula.stockmanagement.productcacheservice.feign.product;

import com.yigitkula.stockmanagement.productcacheservice.enums.Language;
import com.yigitkula.stockmanagement.productcacheservice.response.InternalApiResponse;
import com.yigitkula.stockmanagement.productcacheservice.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("${feign.product-service.name}")
public interface ProductServiceFeignClient {

    @GetMapping(value = "/api/1.0/product/{language}/get/{productId}")
    InternalApiResponse<ProductResponse> getProduct(@PathVariable Language language,
                                                    @PathVariable Long productId);
}
