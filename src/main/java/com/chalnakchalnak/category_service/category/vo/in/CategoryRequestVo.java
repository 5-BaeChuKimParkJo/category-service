package com.chalnakchalnak.category_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryRequestVo {

    private Long categoryId;
    private String name;
    private String description;
    private String imageUrl;
}
