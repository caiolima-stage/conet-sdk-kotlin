package com.example.conetsdk

import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.Type
import org.web3j.abi.datatypes.Utf8String
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.http.HttpService
import org.web3j.tx.gas.ContractGasProvider
import org.web3j.tx.gas.DefaultGasProvider
import java.math.BigInteger

object Contract {
    const val CONET_RPC = "https://rpc.conet.network"

    private lateinit var gasProvider: ContractGasProvider
    private lateinit var web3j: Web3j

    fun initialize() {
        gasProvider = DefaultGasProvider()
        web3j = Web3j.build(HttpService(CONET_RPC))
    }

    fun callReadContract(walletAddress: String, methodName: String, contractAddress: String, returnType: List<TypeReference<out Type<*>>>, param: Any?): Any {
        val inputParameters: List<Type<*>> = if (param != null) {
            when (param) {
                is String -> listOf(Utf8String(param))
                is Int -> listOf(Uint256(param.toLong()))
                is BigInteger -> listOf(Uint256(param))
                else -> throw IllegalArgumentException("Unsupported parameter type: ${param::class.simpleName}")
            }
        } else {
            listOf()
        }

        val function = Function(
            methodName,
            inputParameters,
            returnType
        )

        val encodedFunction = FunctionEncoder.encode(function)

        val response = web3j.ethCall(
            Transaction.createEthCallTransaction(
                walletAddress,
                contractAddress,
                encodedFunction
            ), DefaultBlockParameterName.LATEST
        ).send()

        val decodedResponse = FunctionReturnDecoder.decode(response.value, function.outputParameters)

        val data = decodedResponse as Any

        return data
    }
}