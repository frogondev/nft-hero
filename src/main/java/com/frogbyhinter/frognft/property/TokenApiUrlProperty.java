package com.frogbyhinter.frognft.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="token-api-url")
public class TokenApiUrlProperty {

    private String template;

}
