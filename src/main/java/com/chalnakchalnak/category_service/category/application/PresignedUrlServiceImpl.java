package com.chalnakchalnak.category_service.category.application;

import com.chalnakchalnak.category_service.category.dto.in.PresignedUrlRequestDto;
import com.chalnakchalnak.category_service.category.dto.in.SaveImageUrlRequestDto;
import com.chalnakchalnak.category_service.category.dto.out.PresignedUrlResponseDto;
import com.chalnakchalnak.category_service.category.infrastructure.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Service
public class PresignedUrlServiceImpl implements PresignedUrlService{

    private final CategoryRepository categoryRepository;
    private final S3Presigner s3Presigner;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Override
    public PresignedUrlResponseDto generatePresignedUrl(PresignedUrlRequestDto presignedUrlRequestDto) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(presignedUrlRequestDto.getFileName())
                .contentType(presignedUrlRequestDto.getContentType())
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(5)) // 5분 유효
                .putObjectRequest(objectRequest)
                .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);

        String presignedUrl = presignedRequest.url().toString();
        String uploadFileUrl = "https://" + bucket + ".s3." + region + ".amazonaws.com/" + presignedUrlRequestDto.getFileName();

        return null;
    }

    @Override
    public void saveImageUrl(SaveImageUrlRequestDto saveImageUrlRequestDto) {

    }
}
