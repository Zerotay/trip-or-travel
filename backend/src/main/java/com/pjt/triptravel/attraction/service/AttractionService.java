package com.pjt.triptravel.attraction.service;

import com.pjt.triptravel.attraction.dto.attraction.*;
import com.pjt.triptravel.attraction.entity.AttractionInfo;
import com.pjt.triptravel.attraction.entity.ContentType;
import com.pjt.triptravel.attraction.repository.AttractionQueryRepository;
import com.pjt.triptravel.attraction.repository.AttractionRepository;
import com.pjt.triptravel.attraction.repository.ContentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final AttractionQueryRepository attractionQueryRepository;
    private final ContentTypeRepository contentTypeRepository;

    public List<ContentTypeDto> getContentTypes() {
        return contentTypeRepository.findAll()
            .stream().map(contentType -> new ContentTypeDto(contentType.getId(), contentType.getName()))
            .collect(Collectors.toList());
    }

    public Map<Long, List<AttractionSearchResult>> findTopRatingByContentType(int top) {
        Map<Long, List<AttractionSearchResult>> result = new HashMap<>();
        List<AttractionSearchResult> attractions = attractionRepository.findTopRatingByContentType(top);
        for (AttractionSearchResult attraction: attractions) {
            if (attraction.getContentTypeId().equals(25L))
                continue;
            Long contentTypeId = attraction.getContentTypeId();
            if (!result.containsKey(contentTypeId))
                result.put(contentTypeId, new ArrayList<>());
            result.get(contentTypeId).add(attraction);
        }
        return result;
    }

    public Slice<AttractionSearchResult> search(AttractionSearchCondition condition, Pageable pageable) {
        if (condition.getSidoCode() == null && condition.getGugunCode() != null)
            throw new IllegalArgumentException("구군 번호만으로 조회할 수 없습니다.");
        return attractionQueryRepository.query(condition, pageable);
    }

    public List<AttractionAroundResult> findWithinRoundRange(AttractionAroundCondition condition) {
        return attractionRepository.findWithinRoundRange(condition.getLatitude(), condition.getLongitude(), condition.getRadiusKm());
    }

    public AttractionDto findOne(Long id) {
        AttractionInfo attractionInfo = attractionRepository.findByIdWithDescription(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관광지 번호입니다."));
        return AttractionDto.of(attractionInfo);
    }
}
