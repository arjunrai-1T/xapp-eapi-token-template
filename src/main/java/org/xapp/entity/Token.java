package org.xapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name="Token")
@Table(name = "Token")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {

    @Id
    @Column(name="tokenId")
    private String tokenId;

    @Column(name="clientAppId")
    private String clientAppId;

    @Column(name="token")
    private String token;

    @Column(name="tokenIssuerID")
    private String tokenIssuerID;

    @Column(name="tokenIssuedAt")
    private LocalDateTime tokenIssuedAt;

    @Column(name="tokenExpiry")
    private LocalDateTime tokenExpiry;
}
