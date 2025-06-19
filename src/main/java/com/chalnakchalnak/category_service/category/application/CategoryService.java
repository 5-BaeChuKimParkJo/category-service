package com.chalnakchalnak.category_service.category.application;

import com.chalnakchalnak.category_service.category.dto.in.CategoryCreateRequestDto;
import com.chalnakchalnak.category_service.category.dto.in.CategoryIdListRequestDto;
import com.chalnakchalnak.category_service.category.dto.in.CategoryUpdateRequestDto;
import com.chalnakchalnak.category_service.category.dto.out.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto getCategory(Long categoryId);

    List<CategoryResponseDto> getCategoryList(CategoryIdListRequestDto categoryIdListRequestDto);

    List<CategoryResponseDto> getCategoryList();

    void createCategory(CategoryCreateRequestDto categoryCreateRequestDto);

    void updateCategory(CategoryUpdateRequestDto categoryRequestDto);

    void deleteCategory(Long categoryId);
}
