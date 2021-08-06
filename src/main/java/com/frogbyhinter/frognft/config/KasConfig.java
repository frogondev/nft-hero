package com.frogbyhinter.frognft.config;

import com.frogbyhinter.frognft.property.KasApiProperty;
import com.klaytn.caver.utils.ChainId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.groundx.caver_ext_kas.CaverExtKAS;

@Configuration
public class KasConfig {

    private final KasApiProperty kasApiProperty;

    public KasConfig(KasApiProperty kasApiProperty) {
        this.kasApiProperty = kasApiProperty;
    }

    @Bean
    @Qualifier("kas17Api")
    public CaverExtKAS kas17Api() {
        CaverExtKAS caver = new CaverExtKAS();
        caver.initKIP17API(
                String.valueOf(ChainId.MAINNET),
                kasApiProperty.getAccessKey(),
                kasApiProperty.getSecretAccessKey(),
                kasApiProperty.getKip17ApiUrl()
        );

        return caver;
    }

}
