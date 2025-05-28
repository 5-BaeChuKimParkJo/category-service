package com.chalnakchalnak.category_service.category.infrastructure;

import com.chalnakchalnak.category_service.category.dto.in.CategoryRequestDto;

public interface CategoryRepositoryCustom {
    long updateCategoryDynamic(CategoryRequestDto categoryUpdateRequestDto);
}
