package com.chalnakchalnak.category_service.category.dto.out;

import com.chalnakchalnak.category_service.category.entity.Category;
import com.chalnakchalnak.category_service.category.vo.in.CategoryResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Getter
@ToString
@NoArgsConstructor
public class CategoryResponseDto {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private Long categoryId;
    private String name;
    private String description;
    private String imageKey;
    private String imageUrl;
    private Boolean isUsed;

    @Builder
    public CategoryResponseDto(Long categoryId, String name, String description, String imageKey, String imageUrl, Boolean isUsed) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.imageKey = imageKey;
        this.imageUrl = imageUrl;
        this.isUsed = isUsed;
    }

    public static CategoryResponseDto from(Category category) {
        return CategoryResponseDto.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .imageKey(category.getImageKey())
                .build();
    }

    public CategoryResponseVo toVo() {
        return CategoryResponseVo.builder()
                .categoryId(categoryId)
                .name(name)
                .description(description)
                .imageUrl(imageKey != null ? "https://" + bucket + ".s3." + region + ".amazonaws.com/" + imageKey : "")
                .build();
    }
}
