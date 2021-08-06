package com.frogbyhinter.frognft.service.persist;

import com.frogbyhinter.frognft.entity.FrogCertificateTokenAttributeEntity;
import com.frogbyhinter.frognft.repository.FrogCertificateTokenAttributeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FrogCertificateTokenAttributePersistService {

    private final FrogCertificateTokenAttributeRepository frogCertificateTokenAttributeRepository;

    public FrogCertificateTokenAttributePersistService(FrogCertificateTokenAttributeRepository frogCertificateTokenAttributeRepository) {
        this.frogCertificateTokenAttributeRepository = frogCertificateTokenAttributeRepository;
    }

    @Transactional
    public List<FrogCertificateTokenAttributeEntity> saveAll(List<FrogCertificateTokenAttributeEntity> entityList) {
        return frogCertificateTokenAttributeRepository.saveAll(entityList);
    }

}
