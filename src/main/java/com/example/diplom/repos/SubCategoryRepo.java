package com.example.diplom.repos;

import com.example.diplom.domain.Category;
import com.example.diplom.domain.SubCategory;
import org.springframework.data.repository.CrudRepository;

public interface SubCategoryRepo extends CrudRepository<SubCategory,Long> {
    SubCategory findByName(String name);
}
