package com.chalnakchalnak.category_service.category.infrastructure;

import com.chalnakchalnak.category_service.category.dto.in.CategoryUpdateRequestDto;

public interface CategoryRepositoryCustom {
    long updateCategoryDynamic(CategoryUpdateRequestDto categoryUpdateRequestDto);
}
