package com.chalnakchalnak.category_service.category.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CategoryVo {

    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private Boolean isUsed;

    @Builder
    public CategoryVo(Long categoryId, String name, String description, String imageUrl, Boolean isUsed) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isUsed = isUsed;
    }
}
