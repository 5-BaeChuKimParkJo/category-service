package com.chalnakchalnak.category_service.category.presentation;

import com.chalnakchalnak.category_service.category.application.PresignedUrlService;
import com.chalnakchalnak.category_service.category.dto.in.PresignedUrlRequestDto;
import com.chalnakchalnak.category_service.category.dto.in.SaveImageUrlRequestDto;
import com.chalnakchalnak.category_service.category.dto.out.PresignedUrlResponseDto;
import com.chalnakchalnak.category_service.category.vo.PresignedUrlRequestVo;
import com.chalnakchalnak.category_service.category.vo.PresignedUrlResponseVo;
import com.chalnakchalnak.category_service.category.vo.SaveImageUrlRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "ProductImage", description = "상품 이미지 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class PresignedUrlController {

    private final PresignedUrlService presignedUrlService;

    @Operation(summary = "AWS S3 Presigned url 요청")
    @PostMapping("/presigned-url")
    public PresignedUrlResponseVo getPresignedUrl(@Valid @RequestBody PresignedUrlRequestVo presignedUrlRequestVo) {
        return presignedUrlService
                .generatePresignedUrl(PresignedUrlRequestDto.toPresignedUrlRequestDto(presignedUrlRequestVo))
                .toVo();
    }

    @Operation(summary = "이미지 url DB저장")
    @PutMapping("/save-url")
    public void saveImageUrl(@Valid @RequestBody SaveImageUrlRequestVo saveImageUrlRequestVo) {
        presignedUrlService.saveImageUrl(SaveImageUrlRequestDto.from(saveImageUrlRequestVo));
    }
}
