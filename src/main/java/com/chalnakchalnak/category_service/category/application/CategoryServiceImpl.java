package com.chalnakchalnak.category_service.category.application;

import com.chalnakchalnak.category_service.category.dto.in.CategoryCreateRequestDto;
import com.chalnakchalnak.category_service.category.dto.in.CategoryIdListRequestDto;
import com.chalnakchalnak.category_service.category.dto.in.CategoryUpdateRequestDto;
import com.chalnakchalnak.category_service.category.entity.Category;
import com.chalnakchalnak.category_service.category.infrastructure.CategoryRepositoryCustom;
import com.chalnakchalnak.category_service.category.dto.out.CategoryResponseDto;
import com.chalnakchalnak.category_service.category.infrastructure.CategoryRepository;
import com.chalnakchalnak.category_service.category.util.CacheUtil;
import com.chalnakchalnak.category_service.common.entity.BaseResponseStatus;
import com.chalnakchalnak.category_service.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryCustom categoryRepositoryCustom;
    private final CacheUtil cacheUtil;

    @Override
    @Cacheable(value = "categoryId", key = "#categoryId")
    public CategoryResponseDto getCategory(Long categoryId) {
        return CategoryResponseDto.from(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY)));
    }

    @Override
    @Cacheable(value = "categoryIdList", key = "#categoryIdListRequestDto.categoryIdList")
    public List<CategoryResponseDto> getCategoryList(CategoryIdListRequestDto categoryIdListRequestDto) {
        return categoryIdListRequestDto.getCategoryIdList()
                .stream().map(categoryId -> CategoryResponseDto.from(categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY))))
                .toList();
    }

    @Override
    @Cacheable(value = "category")
    public List<CategoryResponseDto> getCategoryList() {
        return categoryRepository.findAll().stream().map(CategoryResponseDto::from).toList();
    }

    @Override
    public void createCategory(CategoryCreateRequestDto categoryCreateRequestDto) {
        if (categoryRepository.existsByName(categoryCreateRequestDto.getName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_CATEGORY);
        }
        categoryRepository.save(categoryCreateRequestDto.toEntity());

        // 관련 캐시 삭제
        cacheUtil.evictMemberCache("category", "");
    }

    @Override
    @Transactional
    public void updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto) {

        Category category = categoryRepository.findById(categoryUpdateRequestDto.getCategoryId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));

        // 아이디가 다른데 이름이 같으면 예외 처리   // 아이디가 같고 이름도 같으면 통과
        if (category.getId() != categoryUpdateRequestDto.getCategoryId()
                && categoryRepository.existsByName(categoryUpdateRequestDto.getName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_CATEGORY);
        }
        categoryRepositoryCustom.updateCategoryDynamic(categoryUpdateRequestDto);

        cacheUtil.evictMemberCache("categoryId", String.valueOf(categoryUpdateRequestDto.getCategoryId()));
        cacheUtil.evictMemberCacheList("categoryIdList", String.valueOf(categoryUpdateRequestDto.getCategoryId()));
        cacheUtil.evictMemberCache("category", "");
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);

        cacheUtil.evictMemberCache("categoryId", String.valueOf(categoryId));
        cacheUtil.evictMemberCacheList("categoryIdList", String.valueOf(categoryId));
        cacheUtil.evictMemberCache("category", "");
    }
}
