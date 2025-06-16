package com.chalnakchalnak.category_service.category.dto.out;

import com.chalnakchalnak.category_service.category.entity.Category;
import com.chalnakchalnak.category_service.category.vo.in.CategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CategoryResponseDto {

    private Long categoryId;
    private String name;
    private String description;
    private String imageKey;
    private Boolean isUsed;

    @Builder
    public CategoryResponseDto(Long categoryId, String name, String description, String imageKey, Boolean isUsed) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageKey = imageKey;
        this.isUsed = isUsed;
    }

    public static CategoryResponseDto from(Category category) {
        return CategoryResponseDto.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .imageKey(category.getImageKey())
                .build();
    }

    public CategoryVo toVo() {
        return CategoryVo.builder()
                .categoryId(categoryId)
                .name(name)
                .description(description)
                .imageKey(imageKey)
                .build();
    }
}
