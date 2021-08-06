package com.frogbyhinter.frognft.repository;

import com.frogbyhinter.frognft.entity.FrogCertificateTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FrogCertificateTokenRepository extends JpaRepository<FrogCertificateTokenEntity, Long> {

    Optional<FrogCertificateTokenEntity> findByIdIs(Long id);

}
