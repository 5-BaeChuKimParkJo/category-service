package com.chalnakchalnak.category_service.category.application;

import com.chalnakchalnak.category_service.category.dto.in.CategoryRequestDto;
import com.chalnakchalnak.category_service.category.infrastructure.CategoryRepositoryCustom;
import com.chalnakchalnak.category_service.category.dto.out.CategoryResponseDto;
import com.chalnakchalnak.category_service.category.infrastructure.CategoryRepository;
import com.chalnakchalnak.category_service.common.entity.BaseResponseStatus;
import com.chalnakchalnak.category_service.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryCustom categoryRepositoryCustom;

    @Override
    public List<CategoryResponseDto> getCategoryList() {
        return categoryRepository.findByIsUsedTrue().stream().map(CategoryResponseDto::from).toList();
    }

    @Override
    public void createCategory(CategoryRequestDto categoryRequestDto) {
        if (categoryRepository.existsByName(categoryRequestDto.getName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_CATEGORY);
        }
        categoryRepository.save(categoryRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void updateCategory(CategoryRequestDto categoryRequestDto) {
        categoryRepositoryCustom.updateCategoryDynamic(categoryRequestDto);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
