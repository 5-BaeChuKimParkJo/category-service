package com.chalnakchalnak.category_service.category.dto.in;

import com.chalnakchalnak.category_service.category.entity.Category;
import com.chalnakchalnak.category_service.category.vo.in.CategoryUpdateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class CategoryUpdateRequestDto {

    private Long categoryId;
    private String name;
    private String description;
    private String imageKey;

    @Builder
    public CategoryUpdateRequestDto(Long categoryId, String name, String description, String imageKey) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageKey = imageKey;
    }

    public static CategoryUpdateRequestDto from(CategoryUpdateRequestVo categoryUpdateRequestVo) {
        return CategoryUpdateRequestDto.builder()
                .categoryId(categoryUpdateRequestVo.getCategoryId())
                .name(categoryUpdateRequestVo.getName())
                .description(categoryUpdateRequestVo.getDescription())
                .imageKey(categoryUpdateRequestVo.getImageKey())
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
