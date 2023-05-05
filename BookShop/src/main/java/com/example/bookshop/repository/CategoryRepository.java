package com.example.bookshop.repository;

import com.example.bookshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
     com.example.bookshop.models.Category findByName(String name);


}
