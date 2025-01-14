package com.pjt.triptravel.attraction.dto.attraction;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AttractionSearchResult {

    private Long id;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String imageUrl;
    private String imageUrl2;
    private Long sidoCode;
    private Long gugunCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String mlevel;
    private int readCount;
    private int likeCount;
    private Long contentTypeId;
    private String contentType;

    @QueryProjection
    public AttractionSearchResult(Long id, String title, String addr1, String addr2, String zipcode, String tel, String imageUrl, String imageUrl2, Long sidoCode, Long gugunCode, BigDecimal latitude, BigDecimal longitude, String mlevel, int readCount, int likeCount, Long contentTypeId, String contentType) {
        this.id = id;
        this.title = title;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.zipcode = zipcode;
        this.tel = tel;
        this.imageUrl = imageUrl;
        this.imageUrl2 = imageUrl2;
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mlevel = mlevel;
        this.readCount = readCount;
        this.likeCount = likeCount;
        this.contentTypeId = contentTypeId;
        this.contentType = contentType;
    }
}
