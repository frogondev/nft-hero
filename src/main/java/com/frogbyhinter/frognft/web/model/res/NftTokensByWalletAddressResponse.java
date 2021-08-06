package com.frogbyhinter.frognft.web.model.res;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


@Data
@JsonPropertyOrder({"page", "size", "totalCount", "lastPage", "isLastPage", "tokenInventory"})
public class NftTokensByWalletAddressResponse {

    private int page;
    private int size;
    private int totalCount;
    private int lastPage;
    private Boolean isLastPage;
    private NftTokenInventory tokenInventory;

}
