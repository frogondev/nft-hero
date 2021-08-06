package com.frogbyhinter.frognft.client.cypress.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class DetailTransfer {

    private Instant createdAt;
    private String parentHash;
    private String fromAddress;
    private String toAddress;
    private String tokenId;
    private String tokenAddress;
    private BlockNumber blockNumber;

}
