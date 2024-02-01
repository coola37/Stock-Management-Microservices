package com.yigitkula.stockmanagement.productcacheservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private long productCreatedDate;
    private long productUpdatedDate;
}
