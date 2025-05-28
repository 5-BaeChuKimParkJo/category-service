package com.chalnakchalnak.category_service.category.dto.in;

import com.chalnakchalnak.category_service.category.entity.Category;
import com.chalnakchalnak.category_service.category.vo.CategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class CategoryRequestDto {

    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private Boolean isUsed;

    @Builder
    public CategoryRequestDto(Long categoryId, String name, String description, String imageUrl, Boolean isUsed) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isUsed = isUsed;
    }

    public static CategoryRequestDto from(CategoryVo categoryVo) {
        return CategoryRequestDto.builder()
                .categoryId(categoryVo.getCategoryId())
                .name(categoryVo.getName())
                .description(categoryVo.getDescription())
                .imageUrl(categoryVo.getImageUrl())
                .isUsed(categoryVo.getIsUsed() == null ? true : categoryVo.getIsUsed())
                .build();
    }

    public Category toEntity() {
        return Category.builder()
                .id(this.categoryId)
                .name(this.name)
                .description(this.description)
                .imageUrl(this.imageUrl)
                .isUsed(this.isUsed)
                .build();
    }
}
