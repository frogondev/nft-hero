package com.frogbyhinter.frognft.web.model.res;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"walletAddress", "tokens"})
public class NftTokenInventory {

    private String walletAddress;
    private List<NftToken> tokens;

}
