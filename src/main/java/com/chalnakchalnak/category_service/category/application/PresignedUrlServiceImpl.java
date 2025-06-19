package com.chalnakchalnak.category_service.category.application;

import com.chalnakchalnak.category_service.category.dto.in.PresignedUrlRequestDto;
import com.chalnakchalnak.category_service.category.dto.in.SaveImageUrlRequestDto;
import com.chalnakchalnak.category_service.category.dto.out.PresignedUrlResponseDto;
import com.chalnakchalnak.category_service.category.entity.Category;
import com.chalnakchalnak.category_service.category.infrastructure.CategoryRepository;
import com.chalnakchalnak.category_service.category.util.PresignedUrlUtil;
import com.chalnakchalnak.category_service.common.entity.BaseResponseStatus;
import com.chalnakchalnak.category_service.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class PresignedUrlServiceImpl implements PresignedUrlService{

    private final CategoryRepository categoryRepository;

    private final String algorithm = "AWS4-HMAC-SHA256";
    private final String service = "s3";

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Override
    public PresignedUrlResponseDto generatePresignedUrl(PresignedUrlRequestDto presignedUrlRequestDto) {
        // 날짜 형식
        Date now = new Date();
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyyMMdd");
        dateFmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = dateFmt.format(now);

        SimpleDateFormat dateTimeFmt = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        dateTimeFmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateTime = dateTimeFmt.format(now);

        // Credential
        String credential = this.accessKey + "/" + date + "/" + this.region + "/" + this.service + "/aws4_request";

        // Policy 문서 생성
        String policyDoc = PresignedUrlUtil.generateDoc(this.bucket,
                this.algorithm,
                credential,
                dateTime,
                presignedUrlRequestDto.getContentType());

        // Policy 인코딩
        Base64.Encoder encoder = Base64.getEncoder();
        String policy = encoder.encodeToString(policyDoc.getBytes(StandardCharsets.UTF_8));

        // 서명 키 생성
        byte[] signingKey = PresignedUrlUtil.getSignatureKey(this.secretKey, date, this.region, this.service);

        // Signature 생성
        String signature = PresignedUrlUtil.bytesToHex(PresignedUrlUtil.hmacSha256(signingKey, policy));

        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("key", presignedUrlRequestDto.getKey());
        fields.put("Content-Type", presignedUrlRequestDto.getContentType());
        fields.put("bucket", this.bucket);
        fields.put("X-Amz-Algorithm", this.algorithm);
        fields.put("X-Amz-Credential", credential);
        fields.put("X-Amz-Date", dateTime);
        fields.put("Policy", policy);
        fields.put("X-Amz-Signature", signature);

        String url = "https://" + this.bucket + ".s3." + this.region + ".amazonaws.com/";

        return PresignedUrlResponseDto.builder()
                .url(url)
                .fields(fields)
                .build();
    }

    @Override
    @Transactional
    public void saveImageUrl(SaveImageUrlRequestDto saveImageUrlRequestDto) {
        Category category = categoryRepository.findById(saveImageUrlRequestDto.getCategoryId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        category.setImageKey(saveImageUrlRequestDto.getProfileImageKey());
    }
}
