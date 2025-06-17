package com.chalnakchalnak.category_service.category.presentation;

import com.chalnakchalnak.category_service.category.application.CategoryService;
import com.chalnakchalnak.category_service.category.dto.in.CategoryIdListRequestDto;
import com.chalnakchalnak.category_service.category.dto.in.CategoryRequestDto;
import com.chalnakchalnak.category_service.category.dto.out.CategoryResponseDto;
import com.chalnakchalnak.category_service.category.vo.in.CategoryIdListRequestVo;
import com.chalnakchalnak.category_service.category.vo.in.CategoryRequestVo;
import com.chalnakchalnak.category_service.category.vo.in.CategoryResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    @Operation(summary = "카테고리 단일 조회")
    @GetMapping("/{categoryId}")
    public CategoryResponseVo getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategory(categoryId).toVo();
    }

    @Operation(summary = "카테고리 리스트 조회")
    @PostMapping("/list")
    public List<CategoryResponseVo> getCategoryLIst(@RequestBody @Valid CategoryIdListRequestVo categoryIdListRequestVo) {
        return categoryService.getCategoryList(CategoryIdListRequestDto.from(categoryIdListRequestVo))
                .stream()
                .map(categoryResponseDto -> categoryResponseDto.toVo())
                .toList();
    }

    @Operation(summary = "카테고리 전체 조회")
    @GetMapping("/list")
    public List<CategoryResponseVo> getCategoryList() {
        return categoryService.getCategoryList()
                        .stream()
                        .map(CategoryResponseDto::toVo)
                        .toList();
    }

    @Operation(summary = "카테고리 생성")
    @PostMapping
    public void createCategory(@RequestBody CategoryRequestVo categoryRequestVo) {
        categoryService.createCategory(CategoryRequestDto.from(categoryRequestVo));
    }

    @Operation(summary = "카테고리 수정")
    @PutMapping
    public void updateCategory(@RequestBody CategoryRequestVo categoryRequestVo) {
        categoryService.updateCategory(CategoryRequestDto.from(categoryRequestVo));
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @Operation(summary = "추천 카테고리 조회")
    @GetMapping("/recommend")
    public List<CategoryResponseVo> getRecommendedCategory() {
        return null;
    }
}
