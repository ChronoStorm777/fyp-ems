
// Connect to Ethereum
// Initialize Web3
let web3;

if (typeof window.ethereum !== 'undefined') {
     web3 = new Web3(window.ethereum);
    ethereum.request({ method: 'eth_requestAccounts' })
        .then(accounts => {
            const contractAddress = '0x358AA13c52544ECCEF6B0ADD0f801012ADAD5eE3'; 
            const abi = [[
                [
                    {
                        "inputs": [
                            {
                                "internalType": "string",
                                "name": "_name",
                                "type": "string"
                            },
                            {
                                "internalType": "string",
                                "name": "_icNumber",
                                "type": "string"
                            },
                            {
                                "internalType": "bool",
                                "name": "_isOKU",
                                "type": "bool"
                            }
                        ],
                        "name": "addAnakAngkat",
                        "outputs": [],
                        "stateMutability": "nonpayable",
                        "type": "function"
                    },
                    {
                        "inputs": [
                            {
                                "internalType": "string",
                                "name": "_name",
                                "type": "string"
                            },
                            {
                                "internalType": "string",
                                "name": "_icNumber",
                                "type": "string"
                            },
                            {
                                "internalType": "bool",
                                "name": "_isOKU",
                                "type": "bool"
                            }
                        ],
                        "name": "addAnakLelaki",
                        "outputs": [],
                        "stateMutability": "nonpayable",
                        "type": "function"
                    },
                    {
                        "inputs": [
                            {
                                "internalType": "string",
                                "name": "_name",
                                "type": "string"
                            },
                            {
                                "internalType": "string",
                                "name": "_icNumber",
                                "type": "string"
                            },
                            {
                                "internalType": "bool",
                                "name": "_isOKU",
                                "type": "bool"
                            }
                        ],
                        "name": "addAnakPerempuan",
                        "outputs": [],
                        "stateMutability": "nonpayable",
                        "type": "function"
                    },
                    {
                        "inputs": [
                            {
                                "internalType": "string",
                                "name": "_name",
                                "type": "string"
                            },
                            {
                                "internalType": "string",
                                "name": "_icNumber",
                                "type": "string"
                            },
                            {
                                "internalType": "string",
                                "name": "_spouseType",
                                "type": "string"
                            }
                        ],
                        "name": "addSpouse",
                        "outputs": [],
                        "stateMutability": "nonpayable",
                        "type": "function"
                    },
                    {
                        "inputs": [
                            {
                                "internalType": "uint256",
                                "name": "_monthlyExpenses",
                                "type": "uint256"
                            },
                            {
                                "internalType": "string",
                                "name": "_estateValue",
                                "type": "string"
                            },
                            {
                                "internalType": "bool",
                                "name": "_hasHibah",
                                "type": "bool"
                            }
                        ],
                        "name": "setWasiatDetails",
                        "outputs": [],
                        "stateMutability": "nonpayable",
                        "type": "function"
                    },
                    {
                        "inputs": [],
                        "stateMutability": "nonpayable",
                        "type": "constructor"
                    },
                    {
                        "anonymous": false,
                        "inputs": [
                            {
                                "indexed": false,
                                "internalType": "string",
                                "name": "heirType",
                                "type": "string"
                            },
                            {
                                "indexed": false,
                                "internalType": "string",
                                "name": "name",
                                "type": "string"
                            },
                            {
                                "indexed": false,
                                "internalType": "string",
                                "name": "icNumber",
                                "type": "string"
                            }
                        ],
                        "name": "HeirAdded",
                        "type": "event"
                    },
                    {
                        "anonymous": false,
                        "inputs": [
                            {
                                "indexed": false,
                                "internalType": "string",
                                "name": "name",
                                "type": "string"
                            },
                            {
                                "indexed": false,
                                "internalType": "string",
                                "name": "icNumber",
                                "type": "string"
                            },
                            {
                                "indexed": false,
                                "internalType": "string",
                                "name": "spouseType",
                                "type": "string"
                            }
                        ],
                        "name": "SpouseAdded",
                        "type": "event"
                    },
                    {
                        "inputs": [
                            {
                                "internalType": "uint256",
                                "name": "",
                                "type": "uint256"
                            }
                        ],
                        "name": "anakAngkats",
                        "outputs": [
                            {
                                "internalType": "string",
                                "name": "name",
                                "type": "string"
                            },
                            {
                                "internalType": "string",
                                "name": "icNumber",
                                "type": "string"
                            },
                            {
                                "internalType": "bool",
                                "name": "isOKU",
                                "type": "bool"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    },
                    {
                        "inputs": [
                            {
                                "internalType": "uint256",
                                "name": "",
                                "type": "uint256"
                            }
                        ],
                        "name": "anakLelakis",
                        "outputs": [
                            {
                                "internalType": "string",
                                "name": "name",
                                "type": "string"
                            },
                            {
                                "internalType": "string",
                                "name": "icNumber",
                                "type": "string"
                            },
                            {
                                "internalType": "bool",
                                "name": "isOKU",
                                "type": "bool"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    },
                    {
                        "inputs": [
                            {
                                "internalType": "uint256",
                                "name": "",
                                "type": "uint256"
                            }
                        ],
                        "name": "anakPerempuans",
                        "outputs": [
                            {
                                "internalType": "string",
                                "name": "name",
                                "type": "string"
                            },
                            {
                                "internalType": "string",
                                "name": "icNumber",
                                "type": "string"
                            },
                            {
                                "internalType": "bool",
                                "name": "isOKU",
                                "type": "bool"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    },
                    {
                        "inputs": [],
                        "name": "estateValue",
                        "outputs": [
                            {
                                "internalType": "string",
                                "name": "",
                                "type": "string"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    },
                    {
                        "inputs": [],
                        "name": "getAnakAngkatCount",
                        "outputs": [
                            {
                                "internalType": "uint256",
                                "name": "",
                                "type": "uint256"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    },
                    {
                        "inputs": [],
                        "name": "getAnakLelakiCount",
                        "outputs": [
                            {
                                "internalType": "uint256",
                                "name": "",
                                "type": "uint256"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    },
                    {
                        "inputs": [],
                        "name": "getAnakPerempuanCount",
                        "outputs": [
                            {
                                "internalType": "uint256",
                                "name": "",
                                "type": "uint256"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    },
                    {
                        "inputs": [],
                        "name": "getSpouseCount",
                        "outputs": [
                            {
                                "internalType": "uint256",
                                "name": "",
                                "type": "uint256"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    },
                    {
                        "inputs": [],
                        "name": "hasHibah",
                        "outputs": [
                            {
                                "internalType": "bool",
                                "name": "",
                                "type": "bool"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    },
                    {
                        "inputs": [],
                        "name": "monthlyExpenses",
                        "outputs": [
                            {
                                "internalType": "uint256",
                                "name": "",
                                "type": "uint256"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    },
                    {
                        "inputs": [],
                        "name": "owner",
                        "outputs": [
                            {
                                "internalType": "address",
                                "name": "",
                                "type": "address"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    },
                    {
                        "inputs": [
                            {
                                "internalType": "uint256",
                                "name": "",
                                "type": "uint256"
                            }
                        ],
                        "name": "spouses",
                        "outputs": [
                            {
                                "internalType": "string",
                                "name": "name",
                                "type": "string"
                            },
                            {
                                "internalType": "string",
                                "name": "icNumber",
                                "type": "string"
                            },
                            {
                                "internalType": "string",
                                "name": "spouseType",
                                "type": "string"
                            }
                        ],
                        "stateMutability": "view",
                        "type": "function"
                    }
                ]
]
            ];

            const contract = new web3.eth.Contract(abi, contractAddress);
            //Handle submit form
            document.querySelector('form').addEventListener('submitButton', async function(event) {
                event.preventDefault();
                
                try {
                    const accounts = await web3.eth.getAccounts();
                    let transactionHashes = [];

                    // Handle Sons (Anak Lelaki)
                    const sonEntries = document.getElementsByClassName('sonEntry');
                    for (let entry of sonEntries) {
                        const name = entry.querySelector('input[name$="name"]').value;
                        const ic = entry.querySelector('input[name$="ic"]').value;
                        const oku = entry.querySelector('select[name$="oku"]').value === 'ya';
                        
                        const tx = await contract.methods.addAnakLelaki(name, ic, oku)
                            .send({ from: accounts[0] });
                        transactionHashes.push(tx.transactionHash);
                    }

                    // Handle Daughters (Anak Perempuan)
                    const daughterEntries = document.getElementsByClassName('daughterEntry');
                    for (let entry of daughterEntries) {
                        const name = entry.querySelector('input[name$="name"]').value;
                        const ic = entry.querySelector('input[name$="ic"]').value;
                        const oku = entry.querySelector('select[name$="oku"]').value === 'ya';
                        
                        const tx = await contract.methods.addAnakPerempuan(name, ic, oku)
                            .send({ from: accounts[0] });
                        transactionHashes.push(tx.transactionHash);
                    }

                    // Handle Adopted Children (Anak Angkat)
                    const angkatEntries = document.getElementsByClassName('angkatEntry');
                    for (let entry of angkatEntries) {
                        const name = entry.querySelector('input[name$="name"]').value;
                        const ic = entry.querySelector('input[name$="ic"]').value;
                        const oku = entry.querySelector('select[name$="oku"]').value === 'ya';
                        
                        const tx = await contract.methods.addAnakAngkat(name, ic, oku)
                            .send({ from: accounts[0] });
                        transactionHashes.push(tx.transactionHash);
                    }

                    // Handle Spouse (Wife/Husband)
                    const gender = document.getElementById('gender').value;
                    if (gender === 'lelaki') {
                        const wifeEntries = document.getElementsByClassName('isteriEntry');
                        for (let entry of wifeEntries) {
                            const name = entry.querySelector('input[name$="name"]').value;
                            const ic = entry.querySelector('input[name$="ic"]').value;
                            const tx = await contract.methods.addSpouse(name, ic, 'isteri')
                                .send({ from: accounts[0] });
                            transactionHashes.push(tx.transactionHash);
                        }
                    } else if (gender === 'perempuan') {
                        const husbandEntry = document.querySelector('.suamiEntry');
                        if (husbandEntry) {
                            const name = husbandEntry.querySelector('input[name$="name"]').value;
                            const ic = husbandEntry.querySelector('input[name$="ic"]').value;
                            const tx = await contract.methods.addSpouse(name, ic, 'suami')
                                .send({ from: accounts[0] });
                            transactionHashes.push(tx.transactionHash);
                        }
                    }

                    // Handle Wasiat Details
                    const monthlyExpenses = document.getElementById('perbelanjaan').value;
                    const estateValue = document.getElementById('anggaran').value;
                    const hasHibah = document.getElementById('hibah').value === 'ya';

                    const detailsTx = await contract.methods.setWasiatDetails(
                        monthlyExpenses,
                        estateValue,
                        hasHibah
                    ).send({ from: accounts[0] });
                    transactionHashes.push(detailsTx.transactionHash);

                    // Create status elements if they don't exist
                    if (!document.getElementById('status')) {
                        const statusDiv = document.createElement('div');
                        statusDiv.id = 'status';
                        document.querySelector('form').appendChild(statusDiv);
                    }
                    if (!document.getElementById('contractAddressSection')) {
                        const addressDiv = document.createElement('div');
                        addressDiv.id = 'contractAddressSection';
                        document.querySelector('form').appendChild(addressDiv);
                    }

                    
                    // Update status
                    document.getElementById('status').innerText = 'All heir information successfully added to blockchain!';
                    document.getElementById('contractAddressSection').innerHTML = `<p>Contract Address: ${contractAddress}</p>`;

                    // Save to backend
                    const response = await fetch('/save-contract-address', {
                        method: 'POST',
                        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                        body: `contractAddress=${encodeURIComponent(contractAddress)}&transactionHash=${transactionHashes.join(',')}`
                    });

                    if (!response.ok) {
                        throw new Error('Failed to save contract details');
                    }

                    // Submit form to server
                    document.querySelector('form').submit();

                } catch (error) {
                    console.error('Error:', error);
                    if (!document.getElementById('status')) {
                        const statusDiv = document.createElement('div');
                        statusDiv.id = 'status';
                        document.querySelector('form').appendChild(statusDiv);
                    }
                    document.getElementById('status').innerText = 'Error submitting heir information to blockchain';
                }
            });
        })
        .catch(error => {
            console.error(error);
            alert('Please install MetaMask or connect to an Ethereum wallet');
        });
} else {
    alert('Please install MetaMask to interact with the contract');
}