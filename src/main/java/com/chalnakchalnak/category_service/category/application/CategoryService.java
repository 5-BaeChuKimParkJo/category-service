package com.chalnakchalnak.category_service.category.application;

import com.chalnakchalnak.category_service.category.dto.in.CategoryRequestDto;
import com.chalnakchalnak.category_service.category.dto.out.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto getCategory(Long categoryId);

    List<CategoryResponseDto> getCategoryList();

    void createCategory(CategoryRequestDto categoryRequestDto);

    void updateCategory(CategoryRequestDto categoryRequestDto);

    void deleteCategory(Long categoryId);
}
