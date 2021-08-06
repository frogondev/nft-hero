package com.frogbyhinter.frognft.web.model.req;

import lombok.Data;

@Data
public class FRCMintReqBody {

    // 상품명
    private String productName;

    // 설명
    private String description;

    // 이미지 URL
    private String imageUrl;

    // 출시일
    private String releaseDate;

    // 사이즈
    private String size;

    // 프로그 거래 번호
    private String frogTransactionNo;

    // 상품상태
    private String productStatus;

    // to Klaytn Address
    private String toKlaytnAddress;

}