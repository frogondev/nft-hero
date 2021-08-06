package com.frogbyhinter.frognft.web.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"name", "image", "sendable", "description", "backgroundColor", "attributes"})
public class TokenUriResponse {

    private String name;

    private String image;

    private Boolean sendable;

    private String description;

    @JsonProperty("background_color")
    private String backgroundColor;

    private List<TokenAttribute> attributes;

}
