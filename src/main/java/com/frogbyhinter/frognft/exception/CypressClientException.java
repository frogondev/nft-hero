package com.frogbyhinter.frognft.exception;

import lombok.Getter;

@Getter
public class CypressClientException extends RuntimeException {

    public CypressClientException(String message) {
        super(message);
    }

}
