package com.frogbyhinter.frognft.property;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="frc-contract")
public class FRCContractProperty {

    private String contractAddress;

    private String symbol;

    private String alias;

    private String tokenName;

}
