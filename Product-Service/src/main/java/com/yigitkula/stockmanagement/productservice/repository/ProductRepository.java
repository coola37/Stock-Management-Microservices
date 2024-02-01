package com.yigitkula.stockmanagement.productservice.repository;

import com.yigitkula.stockmanagement.productservice.repository.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getByProductIdAndDeletedFalse(long productId);
    List<Product> getAllByDeletedFalse();

}
