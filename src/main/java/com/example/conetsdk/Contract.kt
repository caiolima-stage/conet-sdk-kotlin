package com.example.conetsdk

import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Utf8String
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.http.HttpService
import org.web3j.tx.gas.ContractGasProvider
import org.web3j.tx.gas.DefaultGasProvider
import org.web3j.utils.Numeric

object Contract {
    const val CONET_Guardian_PlanV7 = "0x35c6f84C5337e110C9190A5efbaC8B850E960384";
    const val CONET_RPC = "https://rpc.conet.network"

    private lateinit var gasProvider: ContractGasProvider
    private lateinit var web3j: Web3j

    fun initialize() {
        gasProvider = DefaultGasProvider()
        web3j = Web3j.build(HttpService(CONET_RPC))
    }

    fun callReadContract(walletAddress: String, methodName: String, contractAddress: String,) {
        val function = Function(
            methodName,
            listOf(),
            listOf(TypeReference.create(Utf8String::class.java))
        )

        val encodedFunction = FunctionEncoder.encode(function);

        val response = web3j.ethCall(
            Transaction.createEthCallTransaction(
                walletAddress,
                contractAddress,
                encodedFunction
            ), DefaultBlockParameterName.LATEST
        ).send()

        if (response.result != null && response.result.isNotEmpty()) {
            val decodedResult = FunctionReturnDecoder.decode(response.result, function.outputParameters)

            println("Decoded result: $decodedResult")

            val resultString = (decodedResult[0] as Utf8String).value
            println("Decoded response: $resultString")
        } else {
            println("No result returned from contract call.")
        }

        println("RESPONSE: " + Numeric.toBigInt(response.result))
    }
}