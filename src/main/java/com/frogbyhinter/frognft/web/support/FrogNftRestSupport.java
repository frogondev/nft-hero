package com.frogbyhinter.frognft.web.support;

import com.frogbyhinter.frognft.web.model.EmptyJsonObject;
import com.frogbyhinter.frognft.web.model.res.NftTokensByWalletAddressResponse;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public abstract class FrogNftRestSupport {

    protected static <T> ResponseEntity<?> success(T data) {
        return ResponseEntity.ok(
                ImmutableMap.of(
                        "status", HttpStatus.OK.value(),
                        "message", "success",
                        "timestamp", Instant.now().getEpochSecond(),
                        "data", data
                )
        );
    }

    protected static ResponseEntity<?> apiNotAuth(String message) {
        return new ResponseEntity<>(
                ImmutableMap.of(
                        "status", HttpStatus.UNAUTHORIZED.value(),
                        "message", message,
                        "timestamp", Instant.now().getEpochSecond(),
                        "data", new EmptyJsonObject()
                ), HttpStatus.UNAUTHORIZED
        );
    }

    protected static ResponseEntity<?> tokenUriNotFound(String message) {
        return new ResponseEntity<>(
                ImmutableMap.of(
                        "message", message,
                        "timestamp", Instant.now().getEpochSecond(),
                        "data", new EmptyJsonObject()
                ), HttpStatus.OK
        );
    }

    protected static <T> ResponseEntity<?> error(String errorMessage) {
        return ResponseEntity.ok(
                ImmutableMap.of(
                        "status", HttpStatus.INTERNAL_SERVER_ERROR,
                        "message", errorMessage,
                        "timestamp", Instant.now().getEpochSecond(),
                        "data", new EmptyJsonObject()
                )
        );
    }

    protected static <T> ResponseEntity<?> tokenInventoryPageResponse(NftTokensByWalletAddressResponse response) {
        return ResponseEntity.ok(
                ImmutableMap.builder()
                        .put("status", HttpStatus.OK.value())
                        .put("message", "success")
                        .put("timestamp", Instant.now().getEpochSecond())
                        .put("page", response.getPage())
                        .put("size", response.getSize())
                        .put("totalCount", response.getTotalCount())
                        .put("lastPage", response.getLastPage())
                        .put("isLastPage", response.getIsLastPage())
                        .put("data", response.getTokenInventory())
                        .build()
        );
    }

}
