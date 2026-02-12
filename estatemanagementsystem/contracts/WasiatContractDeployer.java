//package com.ems.estatemanagementsystem;

import contracts;
import com.ems.estatemanagementsystem.entity.Wasiat;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.crypto.Credentials;
import org.web3j.generated.contracts.Wasiat;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

public class WasiatContractDeployer {

    private static final String INFURA_URL = "https://mainnet.infura.io/v3/YOUR_INFURA_PROJECT_ID"; // Replace with your Infura URL
    private static final String PRIVATE_KEY = "YOUR_PRIVATE_KEY"; // Replace with your private key

    public static void main(String[] args) throws Exception {
        // Connect to the Ethereum network
        Web3j web3j = Web3j.build(new HttpService(INFURA_URL));

        // Load credentials using private key
        Credentials credentials = Credentials.create(PRIVATE_KEY);

        // Deploy the Wasiat contract
        Wasiat wasiatContract = Wasiat.deploy(
                web3j, 
                credentials, 
                new DefaultGasProvider()
        ).send();

        // Print the contract address
        String contractAddress = wasiatContract.getContractAddress();
        System.out.println("Wasiat Contract deployed at address: " + contractAddress);

        // Add names to the Wasiat list
        addNameToWasiat(wasiatContract, "John Doe");
        addNameToWasiat(wasiatContract, "Jane Doe");

        // Retrieve names from the Wasiat list
        getWasiat(wasiatContract);
    }

    // Add a name to the Wasiat list
    private static void addNameToWasiat(Wasiat wasiatContract, String name) throws Exception {
        TransactionReceipt receipt = wasiatContract.addWasiat(name).send();
        System.out.println("Name added: " + name + " | Transaction hash: " + receipt.getTransactionHash());
    }

    // Get and print the names in the Wasiat list
    private static void getWasiat(Wasiat wasiatContract) throws Exception {
        BigInteger count = wasiatContract.getWasiatCount().send();
        System.out.println("Total names in Wasiat: " + count);

        for (int i = 0; i < count.intValue(); i++) {
            String name = wasiatContract.getWasiat(BigInteger.valueOf(i)).send();
            System.out.println("Name at index " + i + ": " + name);
        }
    }
}