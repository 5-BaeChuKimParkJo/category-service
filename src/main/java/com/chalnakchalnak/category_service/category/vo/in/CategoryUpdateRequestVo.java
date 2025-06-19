package com.chalnakchalnak.category_service.category.vo.in;

import lombok.Getter;

@Getter
public class CategoryUpdateRequestVo {

    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;
}
