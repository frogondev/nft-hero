package com.frogbyhinter.frognft.service.persist;

import com.frogbyhinter.frognft.entity.FrogCertificateTokenEntity;
import com.frogbyhinter.frognft.repository.FrogCertificateTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FrogCertificateTokenPersistService {

    private final FrogCertificateTokenRepository frogCertificateTokenRepository;

    public FrogCertificateTokenPersistService(FrogCertificateTokenRepository frogCertificateTokenRepository) {
        this.frogCertificateTokenRepository = frogCertificateTokenRepository;
    }

    @Transactional
    public FrogCertificateTokenEntity save(FrogCertificateTokenEntity entity) {
        return frogCertificateTokenRepository.save(entity);
    }

}
