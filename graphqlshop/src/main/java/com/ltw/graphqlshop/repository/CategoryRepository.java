package com.ltw.graphqlshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ltw.graphqlshop.entity.Category;
public interface CategoryRepository extends JpaRepository<Category, Long> {}
