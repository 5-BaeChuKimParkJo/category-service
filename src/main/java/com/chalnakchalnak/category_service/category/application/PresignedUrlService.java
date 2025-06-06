package com.chalnakchalnak.category_service.category.application;

import com.chalnakchalnak.category_service.category.dto.in.PresignedUrlRequestDto;
import com.chalnakchalnak.category_service.category.dto.in.SaveImageUrlRequestDto;
import com.chalnakchalnak.category_service.category.dto.out.PresignedUrlResponseDto;

public interface PresignedUrlService {

    PresignedUrlResponseDto generatePresignedUrl(PresignedUrlRequestDto presignedUrlRequestDto);

    void saveImageUrl(SaveImageUrlRequestDto saveImageUrlRequestDto);
}
