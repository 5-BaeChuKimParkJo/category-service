package com.chalnakchalnak.category_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CategoryVo {

    private Long categoryId;
    private String name;
    private String description;
    private String imageKey;

    @Builder
    public CategoryVo(Long categoryId, String name, String description, String imageKey) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageKey = imageKey;
    }
}
