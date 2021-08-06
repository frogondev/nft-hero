package com.frogbyhinter.frognft.service;

import com.frogbyhinter.frognft.client.cypress.CypressClient;
import com.frogbyhinter.frognft.client.cypress.dto.CypressResponse;
import com.frogbyhinter.frognft.client.cypress.dto.DetailTransfer;
import com.frogbyhinter.frognft.client.cypress.dto.Inventory;
import com.frogbyhinter.frognft.web.model.res.NftToken;
import com.frogbyhinter.frognft.web.model.res.NftTokenInventory;
import com.frogbyhinter.frognft.web.model.res.NftTokensByWalletAddressResponse;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FRCService {

    private final CypressClient cypressClient;
    private final String FRCTokenAddress = "0x8f957f9d4eae345f6d23afff8c8a1fff83d564d2";

    public FRCService(CypressClient cypressClient) {
        this.cypressClient = cypressClient;
    }

    public NftTokensByWalletAddressResponse getFrcTokenListByAddress(String walletAddress, int page, int size) {

        final CypressResponse<List<Inventory>> inventories = cypressClient.getInventoriesByTokenAddressAndWalletAddress(
                FRCTokenAddress,
                walletAddress,
                page,
                size
        );

        NftTokensByWalletAddressResponse nftTokensByWalletAddressResponse = new NftTokensByWalletAddressResponse();
        nftTokensByWalletAddressResponse.setPage(page);
        nftTokensByWalletAddressResponse.setSize(size);
        nftTokensByWalletAddressResponse.setTotalCount(inventories.getTotal());
        nftTokensByWalletAddressResponse.setLastPage(lastPage(page, size, inventories.getTotal()));
        nftTokensByWalletAddressResponse.setIsLastPage(nftTokensByWalletAddressResponse.getLastPage() == page);

        final List<NftToken> tokens = inventories.getResult().stream().map(inventory -> {
            final CypressResponse<List<DetailTransfer>> detailTransfers = cypressClient
                    .getDetailTransferByTokenAddressAndTokenId(FRCTokenAddress, inventory.getTokenId());
            final DetailTransfer detailTransfer = detailTransfers.getResult().get(0);

            return new NftToken(
                    detailTransfer.getTokenId(),
                    inventory.getTokenURI(),
                    detailTransfer.getBlockNumber().getBlockNumber(),
                    detailTransfer.getParentHash()
            );
        }).collect(Collectors.toList());
        nftTokensByWalletAddressResponse.setTokenInventory(new NftTokenInventory(walletAddress, tokens));

        return nftTokensByWalletAddressResponse;
    }

    private int lastPage(int page, int size, int totalCount) {
        int totalPage = totalCount / size;
        if (totalCount % size > 0) {
            totalPage++;
        }

        int startPage = ((page - 1) / totalCount) * totalCount + 1;
        int endPage = startPage + totalCount - 1;

        if (endPage > totalPage) {
            endPage = totalPage;
        }

        return endPage;
    }

}
