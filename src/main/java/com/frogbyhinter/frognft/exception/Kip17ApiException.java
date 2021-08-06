package com.frogbyhinter.frognft.exception;

import lombok.Getter;

@Getter
public class Kip17ApiException extends RuntimeException {

    public Kip17ApiException(String message) {
        super(message);
    }

}
