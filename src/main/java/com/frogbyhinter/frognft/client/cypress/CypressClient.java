package com.frogbyhinter.frognft.client.cypress;

import com.frogbyhinter.frognft.client.cypress.dto.CypressResponse;
import com.frogbyhinter.frognft.client.cypress.dto.DetailTransfer;
import com.frogbyhinter.frognft.client.cypress.dto.Inventory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CypressClient {

    private final RestTemplate restTemplate;
    private final String CYPRESS_URI_PREFIX = "https://api-cypress.scope.klaytn.com";

    public CypressClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.restTemplate.setErrorHandler(new CypressClientErrorHandler());
    }

    /**
     *
     * @param tokenAddress
     * @param walletAddress
     * @param page
     * @param size
     * @return
     */
    public CypressResponse<List<Inventory>> getInventoriesByTokenAddressAndWalletAddress(
            String tokenAddress,
            String walletAddress,
            int page,
            int size
    ) {
        final String URI = CYPRESS_URI_PREFIX + "/v1/tokens/{tokenAddress}/inventory";
        final Map<String, String> urlParams = new HashMap<>();
        urlParams.put("tokenAddress", tokenAddress);

        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URI)
                .queryParam("key", walletAddress)
                .queryParam("page", page)
                .queryParam("limit", size);

        final ResponseEntity<CypressResponse<List<Inventory>>> exchange = restTemplate.exchange(
                builder.buildAndExpand(urlParams).toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CypressResponse<List<Inventory>>>() {
                });

        return exchange.getBody();
    }

    /**
     *
     * @param tokenAddress
     * @param tokenId
     * @return
     */
    public CypressResponse<List<DetailTransfer>> getDetailTransferByTokenAddressAndTokenId(
            String tokenAddress,
            String tokenId
    ) {
        final String URI = CYPRESS_URI_PREFIX + "/v1/tokens/{tokenAddress}/details/{tokenId}/transfers";
        final Map<String, String> urlParams = new HashMap<>();
        urlParams.put("tokenAddress", tokenAddress);
        urlParams.put("tokenId", tokenId);

        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URI);

        final ResponseEntity<CypressResponse<List<DetailTransfer>>> exchange = restTemplate.exchange(
                builder.buildAndExpand(urlParams).toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CypressResponse<List<DetailTransfer>>>() {
                });

        return exchange.getBody();
    }


}
