package com.chalnakchalnak.category_service.category.application;

import com.chalnakchalnak.category_service.category.dto.in.CategoryRequestDto;
import com.chalnakchalnak.category_service.category.entity.Category;
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
        return categoryRepository.findAll().stream().map(CategoryResponseDto::from).toList();
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

        Category category = categoryRepository.findById(categoryRequestDto.getCategoryId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));

        // 아이디가 다른데 이름이 같으면 예외 처리   // 아이디가 같고 이름도 같으면 통과
        if (category.getId() != categoryRequestDto.getCategoryId()
                && categoryRepository.existsByName(categoryRequestDto.getName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_CATEGORY);
        }
        categoryRepositoryCustom.updateCategoryDynamic(categoryRequestDto);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
