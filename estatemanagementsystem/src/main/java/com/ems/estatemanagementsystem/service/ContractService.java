package com.ems.estatemanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ems.estatemanagementsystem.entity.Contract;
import com.ems.estatemanagementsystem.entity.ContractDetail;
import com.ems.estatemanagementsystem.repository.ContractRepository;
import java.time.LocalDateTime;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save the contract with its details (address, date,)
    public void saveContract(String transactionHash,String contractDate) {
        // Create a new Contract entity
        Contract contract = new Contract();

        // Create a new ContractDetail and set the transaction hash
        ContractDetail contractDetail = new ContractDetail(transactionHash, LocalDateTime.now().toString());
        contractDetail.setTransactionHash(transactionHash);

        // Set the ContractDetail in the Contract entity
        contract.setContractDetail(contractDetail);

        // Save the Contract entity to the database
        contractRepository.save(contract);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll(); 
    }

    // public List<Contract> getContractsByUserId(Long userId) {
    //      TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getContractsByUserId'");
    //  }
}
