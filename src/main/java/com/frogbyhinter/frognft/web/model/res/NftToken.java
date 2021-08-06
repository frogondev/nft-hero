package com.frogbyhinter.frognft.web.model.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NftToken {
    
    private String tokenId;
    private String tokenURI;
    private String blockNumber;
    private String txHash;
    
}
