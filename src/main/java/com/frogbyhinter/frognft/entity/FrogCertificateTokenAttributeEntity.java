package com.frogbyhinter.frognft.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Entity
@Table(name = "frogc_token_attribute")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class FrogCertificateTokenAttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "trait_type")
    private String traitType;

    private String value;

    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Builder
    public FrogCertificateTokenAttributeEntity(Long tokenId, String traitType, String value) {
        this.tokenId = tokenId;
        this.traitType = traitType;
        this.value = value;
    }

}