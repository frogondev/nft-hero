package com.frogbyhinter.frognft.client.cypress.dto;

import lombok.Data;

@Data
public class BlockNumber {

    private String id;
    private String createdAt;
    private String updatedAt;
    private String parentHash;
    private String fromAddress;
    private String toAddress;
    private String tokenId;
    private String tokenAddress;
    private String blockNumber;

}
