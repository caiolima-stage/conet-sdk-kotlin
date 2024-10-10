package com.example.conetsdk

import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import org.web3j.crypto.WalletUtils
import org.web3j.utils.Numeric
import java.security.SecureRandom

object ConetSDK {
    fun generateMnemonic(): String {
        val initialEntropy = ByteArray(16)
        SecureRandom().nextBytes(initialEntropy)
        return MnemonicUtils.generateMnemonic(initialEntropy)
    }

    fun createWalletFromMnemonic(mnemonic: String): Map<String, String> {
        val seed = MnemonicUtils.generateSeed(mnemonic, null)

        val masterKeyPair = Bip32ECKeyPair.generateKeyPair(seed)

        val derivationPath = intArrayOf(44 or Bip32ECKeyPair.HARDENED_BIT, 60 or Bip32ECKeyPair.HARDENED_BIT, 0 or Bip32ECKeyPair.HARDENED_BIT, 0, 0)
        val derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeyPair, derivationPath)

        val credentials = Credentials.create(derivedKeyPair)

        val privateKey = Numeric.toHexStringNoPrefix(derivedKeyPair.privateKey)
        val walletAddress = credentials.address

        return mapOf(
            "mnemonic" to mnemonic,
            "privateKey" to privateKey,
            "address" to walletAddress
        )
    }

    fun createWallet(): Map<String, String?> {
        val mnemonic = generateMnemonic()
        val walletInfo = createWalletFromMnemonic(mnemonic)

        return walletInfo
    }
}