package com.frogbyhinter.frognft.web.advice;

import com.frogbyhinter.frognft.exception.CypressClientException;
import com.frogbyhinter.frognft.exception.FRCTokenUriNotFoundException;
import com.frogbyhinter.frognft.exception.FrogNftApiAuthException;
import com.frogbyhinter.frognft.exception.Kip17ApiException;
import com.frogbyhinter.frognft.web.support.FrogNftRestSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FrogNftRestAdvice extends FrogNftRestSupport {

    @ExceptionHandler(FrogNftApiAuthException.class)
    public ResponseEntity handleApiNotAuthException(FrogNftApiAuthException ex) {
        return apiNotAuth(ex.getMessage());
    }

    @ExceptionHandler(FRCTokenUriNotFoundException.class)
    public ResponseEntity handleFRCTokenUriNotFoundException(FRCTokenUriNotFoundException ex) {
        return tokenUriNotFound(ex.getMessage());
    }

    @ExceptionHandler(Kip17ApiException.class)
    public ResponseEntity handleKip17ApiException(Kip17ApiException ex) {
        return error(ex.getMessage());
    }

    @ExceptionHandler(CypressClientException.class)
    public ResponseEntity handleCypressClientException(CypressClientException ex) {
        return error(ex.getMessage());
    }

}
