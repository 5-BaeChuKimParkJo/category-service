package com.chalnakchalnak.category_service.category.dto.out;

import com.chalnakchalnak.category_service.category.entity.Category;
import com.chalnakchalnak.category_service.category.vo.in.CategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor
public class CategoryResponseDto implements Serializable {

    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private Boolean isUsed;

    @Builder
    public CategoryResponseDto(Long categoryId, String name, String description, String imageUrl, Boolean isUsed) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isUsed = isUsed;
    }

    public static CategoryResponseDto from(Category category) {
        return CategoryResponseDto.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .imageUrl(category.getImageUrl())
                .build();
    }

    public CategoryVo toVo() {
        return CategoryVo.builder()
                .categoryId(categoryId)
                .name(name)
                .description(description)
                .imageUrl(imageUrl)
                .build();
    }
}
