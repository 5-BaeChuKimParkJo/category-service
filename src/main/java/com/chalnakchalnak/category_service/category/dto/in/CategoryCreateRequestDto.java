package com.chalnakchalnak.category_service.category.dto.in;

import com.chalnakchalnak.category_service.category.entity.Category;
import com.chalnakchalnak.category_service.category.vo.in.CategoryCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryCreateRequestDto {

    private String name;
    private String description;
    private String imageKey;

    @Builder
    public CategoryCreateRequestDto(String name, String description, String imageKey) {
                this.name = name;
        this.description = description;
        this.imageKey = imageKey;
    }

    public static CategoryCreateRequestDto from(CategoryCreateRequestVo categoryCreateRequestVo) {
        return CategoryCreateRequestDto.builder()
                .name(categoryCreateRequestVo.getName())
                .description(categoryCreateRequestVo.getDescription())
                .imageKey(categoryCreateRequestVo.getImageKey())
                .build();
    }

    public Category toEntity() {
        return Category.builder()
                .name(this.name)
                .description(this.description)
                .imageKey(this.imageKey)
                .build();
    }
}
