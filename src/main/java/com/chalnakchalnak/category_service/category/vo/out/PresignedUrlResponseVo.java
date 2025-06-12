package com.chalnakchalnak.category_service.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PresignedUrlResponseVo {

    private String presignedUrl;
    private String uploadFileUrl;

    @Builder
    public PresignedUrlResponseVo(String presignedUrl, String uploadFileUrl) {
        this.presignedUrl = presignedUrl;
        this.uploadFileUrl = uploadFileUrl;
    }
}
