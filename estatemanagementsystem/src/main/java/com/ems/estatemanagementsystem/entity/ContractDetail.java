package com.ems.estatemanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContractDetail {

    @Column(name =" transaction_hash")
    private String transactionHash;

    @Column(name = "contract_date")
    private String contractDate;
}