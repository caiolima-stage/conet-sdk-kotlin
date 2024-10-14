
import android.content.Context
import android.content.SharedPreferences
import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import org.web3j.utils.Numeric
import java.security.SecureRandom

object ConetSDK {
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun initialize(context: Context) {
        preferences = context.getSharedPreferences("wallet", Context.MODE_PRIVATE)
        editor = preferences.edit()
    }

    fun getWallet(): Map<String, String>? {
        val keys = listOf("mnemonic", "private-key", "address")
        val walletData = mutableMapOf<String, String>()

        for (key in keys) {
            val value = preferences.getString(key, null)
            if (value != null) {
                walletData[key] = value
            }
        }

        return if (walletData.size == keys.size) {
            walletData
        } else {
            null
        }
    }

    fun saveWallet(wallet: Map<String, String>) {
        val editor = preferences.edit()
        for ((key, value) in wallet) {
            editor.putString(key, value)
        }

        editor.apply()
    }

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
            "private-key" to privateKey,
            "address" to walletAddress
        )
    }

    fun createWalletIfNotExists(): Map<String, String?> {
        val existingWallet = getWallet()

        if (existingWallet != null) {
            println("WALLET EXISTS: " + existingWallet["private-key"])

            return existingWallet
        } else {
            println("NO WALLET")

            val mnemonic = generateMnemonic()
            val walletInfo = createWalletFromMnemonic(mnemonic)

            saveWallet(walletInfo)

            return walletInfo
        }
    }
}
