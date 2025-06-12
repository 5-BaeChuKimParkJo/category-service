package com.chalnakchalnak.category_service.category.dto.in;

import com.chalnakchalnak.category_service.category.vo.in.CategoryIdListRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryIdListRequestDto {

    List<Long> categoryIdList;

    @Builder
    public CategoryIdListRequestDto(List<Long> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }

    public static CategoryIdListRequestDto from(CategoryIdListRequestVo categoryIdListRequestVo) {
        return CategoryIdListRequestDto.builder()
                .categoryIdList(categoryIdListRequestVo.getCategoryIdList())
                .build();
    }
}
