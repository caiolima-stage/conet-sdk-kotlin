package com.example.conetsdk

object ContractABIS {
    const val CONET_NODES_ABI = "[\n" +
            "    {\n" +
            "        \"inputs\": [],\n" +
            "        \"stateMutability\": \"nonpayable\",\n" +
            "        \"type\": \"constructor\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"anonymous\": false,\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"indexed\": true,\n" +
            "                \"internalType\": \"bytes32\",\n" +
            "                \"name\": \"ipAddr\",\n" +
            "                \"type\": \"bytes32\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"indexed\": true,\n" +
            "                \"internalType\": \"bytes32\",\n" +
            "                \"name\": \"regin\",\n" +
            "                \"type\": \"bytes32\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"deleteIPAddr\",\n" +
            "        \"type\": \"event\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"regionName\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"addRegion\",\n" +
            "        \"outputs\": [],\n" +
            "        \"stateMutability\": \"nonpayable\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"address\",\n" +
            "                \"name\": \"\",\n" +
            "                \"type\": \"address\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"adminList\",\n" +
            "        \"outputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"bool\",\n" +
            "                \"name\": \"\",\n" +
            "                \"type\": \"bool\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"stateMutability\": \"view\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"address\",\n" +
            "                \"name\": \"addr\",\n" +
            "                \"type\": \"address\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"internalType\": \"bool\",\n" +
            "                \"name\": \"status\",\n" +
            "                \"type\": \"bool\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"changeAddressInAdminlist\",\n" +
            "        \"outputs\": [],\n" +
            "        \"stateMutability\": \"nonpayable\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"bytes32\",\n" +
            "                \"name\": \"ipaddressHash\",\n" +
            "                \"type\": \"bytes32\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"deleteIpAddress\",\n" +
            "        \"outputs\": [],\n" +
            "        \"stateMutability\": \"nonpayable\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"regionName\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"deleteRegion\",\n" +
            "        \"outputs\": [],\n" +
            "        \"stateMutability\": \"nonpayable\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [],\n" +
            "        \"name\": \"getAllRegions\",\n" +
            "        \"outputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string[]\",\n" +
            "                \"name\": \"allRegions\",\n" +
            "                \"type\": \"string[]\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"stateMutability\": \"view\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"ipAddr\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"getIPAddressHash\",\n" +
            "        \"outputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"bytes32\",\n" +
            "                \"name\": \"ipAddrHash\",\n" +
            "                \"type\": \"bytes32\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"stateMutability\": \"pure\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"ipaddress\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"getIpAddressOwn\",\n" +
            "        \"outputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"address\",\n" +
            "                \"name\": \"ownership\",\n" +
            "                \"type\": \"address\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"stateMutability\": \"view\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"ipaddress\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"getIpAddressReg\",\n" +
            "        \"outputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"regionName\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"stateMutability\": \"view\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"uint256\",\n" +
            "                \"name\": \"id\",\n" +
            "                \"type\": \"uint256\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"getNodeInfoById\",\n" +
            "        \"outputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"ipaddress\",\n" +
            "                \"type\": \"string\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"regionName\",\n" +
            "                \"type\": \"string\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"pgp\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"stateMutability\": \"view\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"ipaddress\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"getNodePGP\",\n" +
            "        \"outputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"pgp\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"stateMutability\": \"view\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"regionName\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"getReginNodes\",\n" +
            "        \"outputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string[]\",\n" +
            "                \"name\": \"nodes\",\n" +
            "                \"type\": \"string[]\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"stateMutability\": \"view\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"regionName\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"getRegionHash\",\n" +
            "        \"outputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"bytes32\",\n" +
            "                \"name\": \"regionHash\",\n" +
            "                \"type\": \"bytes32\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"stateMutability\": \"pure\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"regionName\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"isRegionExisting\",\n" +
            "        \"outputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"bool\",\n" +
            "                \"name\": \"region_existing\",\n" +
            "                \"type\": \"bool\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"stateMutability\": \"view\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"ipaddress\",\n" +
            "                \"type\": \"string\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"pgp\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"modify_node_pgp\",\n" +
            "        \"outputs\": [],\n" +
            "        \"stateMutability\": \"nonpayable\",\n" +
            "        \"type\": \"function\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"inputs\": [\n" +
            "            {\n" +
            "                \"internalType\": \"uint256\",\n" +
            "                \"name\": \"id\",\n" +
            "                \"type\": \"uint256\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"ipaddress\",\n" +
            "                \"type\": \"string\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"regionName\",\n" +
            "                \"type\": \"string\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"internalType\": \"string\",\n" +
            "                \"name\": \"pgp\",\n" +
            "                \"type\": \"string\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"modify_nodes\",\n" +
            "        \"outputs\": [],\n" +
            "        \"stateMutability\": \"nonpayable\",\n" +
            "        \"type\": \"function\"\n" +
            "    }\n" +
            "]"
}