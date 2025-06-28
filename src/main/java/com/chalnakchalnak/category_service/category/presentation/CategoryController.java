package com.chalnakchalnak.category_service.category.presentation;

import com.chalnakchalnak.category_service.category.application.CategoryService;
import com.chalnakchalnak.category_service.category.dto.in.CategoryCreateRequestDto;
import com.chalnakchalnak.category_service.category.dto.in.CategoryIdListRequestDto;
import com.chalnakchalnak.category_service.category.dto.in.CategoryUpdateRequestDto;
import com.chalnakchalnak.category_service.category.vo.in.CategoryCreateRequestVo;
import com.chalnakchalnak.category_service.category.vo.in.CategoryIdListRequestVo;
import com.chalnakchalnak.category_service.category.vo.in.CategoryUpdateRequestVo;
import com.chalnakchalnak.category_service.category.vo.in.CategoryResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "Category", description = "카테고리 관련 API")
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    private final CategoryService categoryService;

    @Operation(summary = "카테고리 단일 조회")
    @GetMapping("/{categoryId}")
    public CategoryResponseVo getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategory(categoryId).toVo(bucket, region);
    }

    @Operation(summary = "카테고리 리스트 조회")
    @PostMapping("/list")
    public List<CategoryResponseVo> getCategoryLIst(@RequestBody @Valid CategoryIdListRequestVo categoryIdListRequestVo) {
        return categoryService.getCategoryList(CategoryIdListRequestDto.from(categoryIdListRequestVo))
                .stream()
                .map(categoryResponseDto -> categoryResponseDto.toVo(bucket, region))
                .toList();
    }

    @Operation(summary = "카테고리 전체 조회")
    @GetMapping("/list")
    public List<CategoryResponseVo> getCategoryList() {
        return categoryService.getCategoryList()
                        .stream()
                        .map(categoryResponseDto -> categoryResponseDto.toVo(bucket, region))
                        .toList();
    }

    @Operation(summary = "카테고리 생성")
    @PostMapping
    public void createCategory(@RequestBody CategoryCreateRequestVo categoryCreateRequestVo) {
        categoryService.createCategory(CategoryCreateRequestDto.from(categoryCreateRequestVo));
    }

    @Operation(summary = "카테고리 수정")
    @PutMapping
    public void updateCategory(@RequestBody CategoryUpdateRequestVo categoryRequestVo) {
        categoryService.updateCategory(CategoryUpdateRequestDto.from(categoryRequestVo));
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
