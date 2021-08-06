package com.frogbyhinter.frognft.exception;

import lombok.Getter;

@Getter
public class FRCTokenUriNotFoundException extends RuntimeException {

    public FRCTokenUriNotFoundException(String message) {
        super(message);
    }

}
