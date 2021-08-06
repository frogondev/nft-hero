package com.frogbyhinter.frognft.client.cypress;

import com.frogbyhinter.frognft.exception.CypressClientException;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CypressClientErrorHandler implements ResponseErrorHandler {

    private final Gson gson = new Gson();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return new DefaultResponseErrorHandler().hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            log.error("{}", response.getRawStatusCode());
        } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            final Reader reader = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
            final CypressErrorResponse errorResponse = gson.fromJson(reader, CypressErrorResponse.class);
            log.error("{}", errorResponse);

            throw new CypressClientException(errorResponse.getMessage());
        }
    }

}