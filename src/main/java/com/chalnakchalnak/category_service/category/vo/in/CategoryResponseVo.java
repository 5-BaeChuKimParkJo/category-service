package com.chalnakchalnak.category_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CategoryResponseVo {

    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;

    @Builder
    public CategoryResponseVo(Long categoryId, String name, String description, String imageUrl) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
