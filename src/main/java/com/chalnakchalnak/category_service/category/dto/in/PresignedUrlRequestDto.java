package com.chalnakchalnak.category_service.category.dto.in;

import com.chalnakchalnak.category_service.category.vo.in.PresignedUrlRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PresignedUrlRequestDto {

    private String key;
    private String contentType;

    @Builder
    public PresignedUrlRequestDto(Long categoryId, String key, String contentType) {
        this.key = key;
        this.contentType = contentType;
    }

    public static PresignedUrlRequestDto toPresignedUrlRequestDto(PresignedUrlRequestVo presignedUrlRequestVo) {

        String ext = presignedUrlRequestVo.getContentType()
                .substring(presignedUrlRequestVo.getContentType().indexOf("/") + 1);

        return PresignedUrlRequestDto.builder()
                .key("category/images/" + UUID.randomUUID() + "." + ext)
                .contentType(presignedUrlRequestVo.getContentType())
                .build();
    }
}
