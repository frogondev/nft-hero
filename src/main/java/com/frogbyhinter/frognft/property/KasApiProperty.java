package com.frogbyhinter.frognft.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="kas-api")
public class KasApiProperty {

    private String accessKey;

    private String secretAccessKey;

    private String authorization;

    private String kip17ApiUrl;

}
