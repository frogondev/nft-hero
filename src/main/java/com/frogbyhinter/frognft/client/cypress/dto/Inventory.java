package com.frogbyhinter.frognft.client.cypress.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class Inventory {

    private Instant createdAt;
    private String address;
    private String tokenId;
    private String tokenAddress;
    private String tokenURI;
    private String blockNumber;

}
