package com.ems.estatemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.estatemanagementsystem.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
