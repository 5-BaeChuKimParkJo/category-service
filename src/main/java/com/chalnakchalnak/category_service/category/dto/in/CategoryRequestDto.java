package com.chalnakchalnak.category_service.category.dto.in;

import com.chalnakchalnak.category_service.category.entity.Category;
import com.chalnakchalnak.category_service.category.vo.in.CategoryVo;
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
    private String imageKey;

    @Builder
    public CategoryRequestDto(Long categoryId, String name, String description, String imageKey) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageKey = imageKey;
    }

    public static CategoryRequestDto from(CategoryVo categoryVo) {
        return CategoryRequestDto.builder()
                .categoryId(categoryVo.getCategoryId())
                .name(categoryVo.getName())
                .description(categoryVo.getDescription())
                .imageKey(categoryVo.getImageKey())
                .build();
    }

    public Category toEntity() {
        return Category.builder()
                .id(this.categoryId)
                .name(this.name)
                .description(this.description)
                .imageKey(this.imageKey)
                .build();
    }
}
