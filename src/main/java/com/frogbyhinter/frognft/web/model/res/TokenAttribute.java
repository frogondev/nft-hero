package com.frogbyhinter.frognft.web.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"traitType", "value"})
public class TokenAttribute {

    @JsonProperty("trait_type")
    private String traitType;

    private String value;

    public TokenAttribute(String traitType, String value) {
        this.traitType = traitType;
        this.value = value;
    }

}
