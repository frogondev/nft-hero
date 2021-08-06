package com.frogbyhinter.frognft.service;

import com.frogbyhinter.frognft.entity.FrogCertificateTokenAttributeEntity;
import com.frogbyhinter.frognft.entity.FrogCertificateTokenEntity;
import com.frogbyhinter.frognft.exception.Kip17ApiException;
import com.frogbyhinter.frognft.property.FRCContractProperty;
import com.frogbyhinter.frognft.property.KasApiProperty;
import com.frogbyhinter.frognft.property.TokenApiUrlProperty;
import com.frogbyhinter.frognft.service.persist.FrogCertificateTokenAttributePersistService;
import com.frogbyhinter.frognft.service.persist.FrogCertificateTokenPersistService;
import com.frogbyhinter.frognft.web.model.req.FRCMintReqBody;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.groundx.caver_ext_kas.CaverExtKAS;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.ApiException;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.kip17.model.Kip17TransactionStatusResponse;

import java.util.List;

@Slf4j
@Service
public class FRCMintService {

    private final FrogCertificateTokenPersistService frogCertificateTokenPersistService;
    private final FrogCertificateTokenAttributePersistService frogCertificateTokenAttributePersistService;
    private final FRCContractProperty frcContractProperty;
    private final TokenApiUrlProperty tokenApiUrlProperty;
    private final CaverExtKAS kas17Api;

    public FRCMintService(FrogCertificateTokenPersistService frogCertificateTokenPersistService,
                          FrogCertificateTokenAttributePersistService frogCertificateTokenAttributePersistService,
                          FRCContractProperty frcContractProperty,
                          TokenApiUrlProperty tokenApiUrlProperty,
                          @Qualifier("kas17Api") CaverExtKAS kas17Api
    ) {
        this.frogCertificateTokenPersistService = frogCertificateTokenPersistService;
        this.frogCertificateTokenAttributePersistService = frogCertificateTokenAttributePersistService;
        this.frcContractProperty = frcContractProperty;
        this.tokenApiUrlProperty = tokenApiUrlProperty;
        this.kas17Api = kas17Api;
    }

    // FRC Token 발행, Token 전송
    @Transactional
    public String mint(FRCMintReqBody reqBody) {
        final FrogCertificateTokenEntity tokenEntity = saveFrogCertificateTokenEntity(
                reqBody.getProductName(), reqBody.getImageUrl(), reqBody.getDescription());

        saveFrogCertificateTokenAttributeEntity(tokenEntity.getId(), reqBody.getReleaseDate(),
                reqBody.getSize(), reqBody.getFrogTransactionNo(), reqBody.getProductStatus());

        final String contractAlias = frcContractProperty.getAlias();
        final String toAddress = reqBody.getToKlaytnAddress();
        final String tokenId = "0x" + Long.toHexString(tokenEntity.getId());
        final String tokenUri = tokenApiUrlProperty.getTemplate()
                .replace("{tokenId}", String.valueOf(tokenEntity.getId()));


        return mint(contractAlias, toAddress, tokenId, tokenUri).getTransactionHash();
    }

    private FrogCertificateTokenEntity saveFrogCertificateTokenEntity(String name, String image, String description) {
        return frogCertificateTokenPersistService.save(FrogCertificateTokenEntity.builder()
                .name(name)
                .image(image)
                .description(description)
                .build()
        );
    }

    private void saveFrogCertificateTokenAttributeEntity(
            Long tokenId,
            String releaseDate,
            String size,
            String frogTransactionNo,
            String productStatus) {

        List<FrogCertificateTokenAttributeEntity> frogCertificateTokenAttributeEntityList = Lists.newArrayList();

        // Attribute (최대 10개 Klip 에서 지원하는듯?)
        frogCertificateTokenAttributeEntityList.add(
                FrogCertificateTokenAttributeEntity.builder()
                        .tokenId(tokenId)
                        .traitType("발매일 (ReleaseDate)")
                        .value(releaseDate)
                        .build()
        );

        frogCertificateTokenAttributeEntityList.add(
                FrogCertificateTokenAttributeEntity.builder()
                        .tokenId(tokenId)
                        .traitType("사이즈 (Size)")
                        .value(size)
                        .build()
        );

        frogCertificateTokenAttributeEntityList.add(
                FrogCertificateTokenAttributeEntity.builder()
                        .tokenId(tokenId)
                        .traitType("프로그 거래 번호 (Frog Transaction No.)")
                        .value(frogTransactionNo)
                        .build()
        );

        frogCertificateTokenAttributeEntityList.add(
                FrogCertificateTokenAttributeEntity.builder()
                        .tokenId(tokenId)
                        .traitType("상품상태 (Product Status)")
                        .value(productStatus)
                        .build()
        );

        frogCertificateTokenAttributePersistService.saveAll(frogCertificateTokenAttributeEntityList);
    }

    private Kip17TransactionStatusResponse mint(String contractAlias, String toAddress, String tokenId, String tokenUri) {
        try {
            final Kip17TransactionStatusResponse response =  kas17Api.kas.kip17.mint(contractAlias, toAddress, tokenId, tokenUri);
            log.info("mint frc tokenId:{}, tx:{}, status:{}", tokenId, response.getTransactionHash(), response.getStatus());
            return response;
        } catch (ApiException e) {
            log.error("{}", e.getMessage());
            throw new Kip17ApiException(e.getMessage());
        }
    }

}
