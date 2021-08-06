package com.frogbyhinter.frognft.exception;

import lombok.Getter;

@Getter
public class FrogNftApiAuthException extends RuntimeException {

    public FrogNftApiAuthException(String message) {
        super(message);
    }

}
