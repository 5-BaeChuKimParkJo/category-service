package com.chalnakchalnak.category_service.category.presentation;

import com.chalnakchalnak.category_service.category.application.CategoryService;
import com.chalnakchalnak.category_service.category.dto.in.CategoryRequestDto;
import com.chalnakchalnak.category_service.category.dto.out.CategoryResponseDto;
import com.chalnakchalnak.category_service.category.vo.CategoryVo;
import com.chalnakchalnak.category_service.common.entity.BaseResponseEntity;
import com.chalnakchalnak.category_service.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "Category", description = "카테고리 관련 API")
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "카테고리 전체 조회")
    @GetMapping("/list")
    public BaseResponseEntity<List<CategoryVo>> getCategory() {
        return new BaseResponseEntity<>(
                categoryService.getCategoryList()
                        .stream()
                        .map(CategoryResponseDto::toVo)
                        .toList());
    }

    @Operation(summary = "카테고리 생성")
    @PostMapping
    public BaseResponseEntity<Void> createCategory(@RequestBody CategoryVo categoryVo) {
        categoryService.createCategory(CategoryRequestDto.from(categoryVo));
        return new BaseResponseEntity<>();
    }

    @Operation(summary = "카테고리 수정")
    @PutMapping
    public BaseResponseEntity<Void> updateCategory(@RequestBody CategoryVo categoryVo) {
        categoryService.updateCategory(CategoryRequestDto.from(categoryVo));
        return new BaseResponseEntity<>();
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{categoryId}")
    public BaseResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new BaseResponseEntity<>();
    }

    @Operation(summary = "추천 카테고리 조회")
    @GetMapping("/recommend")
    public BaseResponseEntity<List<CategoryVo>> getRecommendedCategory() {
        return null;
    }
}
