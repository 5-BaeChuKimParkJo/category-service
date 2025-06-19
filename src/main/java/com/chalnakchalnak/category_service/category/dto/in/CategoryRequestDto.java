package com.chalnakchalnak.category_service.category.dto.in;

import com.chalnakchalnak.category_service.category.entity.Category;
import com.chalnakchalnak.category_service.category.vo.in.CategoryRequestVo;
import com.chalnakchalnak.category_service.category.vo.in.CategoryResponseVo;
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
    private String imageKey;

    @Builder
    public CategoryRequestDto(Long categoryId, String name, String description, String imageUrl, String imageKey) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageKey = imageKey;
    }

    public static CategoryRequestDto from(CategoryRequestVo categoryRequestVo) {
        return CategoryRequestDto.builder()
                .categoryId(categoryRequestVo.getCategoryId())
                .name(categoryRequestVo.getName())
                .description(categoryRequestVo.getDescription())
                .imageUrl(categoryRequestVo.getImageUrl())
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
