package com.example.LocalExpertBackend.company.companyPage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "contract")
public class CompanyPageContractEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long companyId;

    private String companyCommentId;
}
