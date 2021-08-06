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
@Table(name = "frogc_token")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class FrogCertificateTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private Boolean sendable;

    private String description;

    private String backgroundColor;

    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Builder
    public FrogCertificateTokenEntity(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.sendable = true;
        this.description = description;
        this.backgroundColor = "#FFFFFF";
    }

}