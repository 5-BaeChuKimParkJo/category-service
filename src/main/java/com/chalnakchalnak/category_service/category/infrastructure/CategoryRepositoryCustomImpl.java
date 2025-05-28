package com.chalnakchalnak.category_service.category.infrastructure;

import com.chalnakchalnak.category_service.category.dto.in.CategoryRequestDto;
import com.chalnakchalnak.category_service.category.entity.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Transactional
    @Override
    public long updateCategoryDynamic(CategoryRequestDto dto) {
        QCategory c = QCategory.category;

        log.info("QCategory: {}", c);

        boolean hasUpdate = false;

        var update = queryFactory.update(c);

        if (dto.getName() != null && !dto.getName().isBlank()) {
            update.set(c.name, dto.getName());
            hasUpdate = true;
        }
        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            update.set(c.description, dto.getDescription());
            hasUpdate = true;
        }
        if (dto.getImageUrl() != null && !dto.getImageUrl().isBlank()) {
            update.set(c.imageUrl, dto.getImageUrl());
            hasUpdate = true;
        }
        if (dto.getIsUsed() != null) {
            update.set(c.isUsed, dto.getIsUsed());
        }

        if (!hasUpdate) {
            // 변경할 데이터가 없으면 예외 대신 0 리턴
            return 0L;
        }

        return update
                .where(c.id.eq(dto.getCategoryId()))
                .execute();
    }

}
