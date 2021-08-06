package com.frogbyhinter.frognft.repository;

import com.frogbyhinter.frognft.entity.FrogCertificateTokenAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FrogCertificateTokenAttributeRepository extends JpaRepository<FrogCertificateTokenAttributeEntity, Long> {

    Optional<List<FrogCertificateTokenAttributeEntity>> findByTokenIdIs(Long tokenId);

}
