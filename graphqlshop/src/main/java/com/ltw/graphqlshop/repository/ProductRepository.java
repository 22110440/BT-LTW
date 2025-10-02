package com.ltw.graphqlshop.repository;

import com.ltw.graphqlshop.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Lấy products theo category id
    List<Product> findDistinctByCategories_Id(Long categoryId);

    // Giá tăng dần
    default List<Product> findAllOrderByPriceAsc() {
        return findAll(Sort.by(Sort.Direction.ASC, "price"));
    }
}
