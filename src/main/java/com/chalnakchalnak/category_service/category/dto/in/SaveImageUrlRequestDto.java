package com.chalnakchalnak.category_service.category.dto.in;

import com.chalnakchalnak.category_service.category.vo.in.SaveImageUrlRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SaveImageUrlRequestDto {

    private String profileImageKey;
    private Long categoryId;

    @Builder
    public SaveImageUrlRequestDto(String profileImageKey,
                                  Long categoryId) {
        this.profileImageKey = profileImageKey;
        this.categoryId = categoryId;
    }

    public static SaveImageUrlRequestDto from(SaveImageUrlRequestVo saveImageUrlRequestVo) {
        return SaveImageUrlRequestDto.builder()
                .profileImageKey(saveImageUrlRequestVo.getImageKey())
                .categoryId(saveImageUrlRequestVo.getCategoryId())
                .build();
    }
}
