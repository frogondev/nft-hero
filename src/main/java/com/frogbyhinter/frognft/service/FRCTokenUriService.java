package com.frogbyhinter.frognft.service;

import com.frogbyhinter.frognft.entity.FrogCertificateTokenAttributeEntity;
import com.frogbyhinter.frognft.entity.FrogCertificateTokenEntity;
import com.frogbyhinter.frognft.exception.FRCTokenUriNotFoundException;
import com.frogbyhinter.frognft.repository.FrogCertificateTokenAttributeRepository;
import com.frogbyhinter.frognft.repository.FrogCertificateTokenRepository;
import com.frogbyhinter.frognft.web.model.res.TokenAttribute;
import com.frogbyhinter.frognft.web.model.res.TokenUriResponse;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FRCTokenUriService {

    private final FrogCertificateTokenRepository frogCertificateTokenRepository;
    private final FrogCertificateTokenAttributeRepository frogCertificateTokenAttributeRepository;

    public FRCTokenUriService(FrogCertificateTokenRepository frogCertificateTokenRepository,
                              FrogCertificateTokenAttributeRepository frogCertificateTokenAttributeRepository
    ) {
        this.frogCertificateTokenRepository = frogCertificateTokenRepository;
        this.frogCertificateTokenAttributeRepository = frogCertificateTokenAttributeRepository;
    }

    @Transactional(readOnly = true)
    public TokenUriResponse getTokenUriByTokenId(Long tokenId) {
        final FrogCertificateTokenEntity tokenEntity = frogCertificateTokenRepository
                .findByIdIs(tokenId).orElseThrow(() -> new FRCTokenUriNotFoundException("undefined"));

        final Optional<List<FrogCertificateTokenAttributeEntity>> tokenAttributeListOptional = frogCertificateTokenAttributeRepository
                .findByTokenIdIs(tokenEntity.getId());

        List<TokenAttribute> tokenAttributeList = Lists.newArrayList();
        if (tokenAttributeListOptional.isPresent()) {
            tokenAttributeList = tokenAttributeListOptional
                    .get()
                    .stream()
                    .map(tokenAttributeEntity -> new TokenAttribute(tokenAttributeEntity.getTraitType(), tokenAttributeEntity.getValue()))
                    .collect(Collectors.toList());
        }

        return TokenUriResponse.builder()
                .name(tokenEntity.getName())
                .image(tokenEntity.getImage())
                .sendable(tokenEntity.getSendable())
                .description(tokenEntity.getDescription())
                .backgroundColor(tokenEntity.getBackgroundColor())
                .attributes(tokenAttributeList)
                .build();
    }

}
