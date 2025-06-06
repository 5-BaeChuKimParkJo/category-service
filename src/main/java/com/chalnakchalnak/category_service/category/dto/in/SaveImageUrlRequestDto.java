package com.chalnakchalnak.category_service.category.dto.in;

import com.chalnakchalnak.category_service.category.vo.SaveImageUrlRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SaveImageUrlRequestDto {

    private String uploadFileUrl;
    private Long categoryId;

    @Builder
    public SaveImageUrlRequestDto(String uploadFileUrl,
                                  Long categoryId) {
        this.uploadFileUrl = uploadFileUrl;
        this.categoryId = categoryId;
    }

    public static SaveImageUrlRequestDto from(SaveImageUrlRequestVo saveImageUrlRequestVo) {
        return SaveImageUrlRequestDto.builder()
                .uploadFileUrl(saveImageUrlRequestVo.getUploadFileUrl())
                .categoryId(saveImageUrlRequestVo.getCategoryId())
                .build();
    }
}
