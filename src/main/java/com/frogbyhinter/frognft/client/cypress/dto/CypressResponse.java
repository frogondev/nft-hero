package com.frogbyhinter.frognft.client.cypress.dto;

import lombok.Data;

@Data
public class CypressResponse <T> {

    private Boolean success;
    private Integer code;
    private T result;
    private String page;
    private Integer total;
    private String limit;

}
