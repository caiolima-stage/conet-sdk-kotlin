
import android.content.Context
import android.content.SharedPreferences
import com.example.conetsdk.Contract
import com.example.conetsdk.PGPUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Utf8String
import org.web3j.crypto.Bip32ECKeyPair
import org.web3j.crypto.Credentials
import org.web3j.crypto.MnemonicUtils
import org.web3j.utils.Numeric
import java.security.SecureRandom

const val CONET_GUARDIAN_NODES_CONTRACT_ADDRESS = "0x9e213e8B155eF24B466eFC09Bcde706ED23C537a"

object ConetSDK {
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var contract: Contract = Contract
    private lateinit var credentials: Credentials

    fun initialize(context: Context) {
        preferences = context.getSharedPreferences("wallet", Context.MODE_PRIVATE)
        editor = preferences.edit()
        contract.initialize()

        val currentWallet = getLocalWallet()

        println("CURRENT WALLET: $currentWallet")

        if (currentWallet != null) {
            credentials = Credentials.create(currentWallet["privateKeyArmor"] as String)
        }
    }

    fun getLocalWallet(): Map<String, Any>? {
        val keys = listOf("publicKeyArmor", "keyID", "isPrimary", "referrer", "isNode", "privateKeyArmor", "hdPath", "index")
        val walletData = mutableMapOf<String, Any>()

        for (key in keys) {
            val value = preferences.getString(key, null)
            if (value != null) {
                walletData[key] = value
            }
        }

        val pgpPrivateKey = preferences.getString("private-key", null)
        val pgpPublicKey = preferences.getString("public-key", null)

        walletData["pgpKey"] = mapOf(
            "private-key" to pgpPrivateKey,
            "public-key" to pgpPublicKey
        )

        return if (walletData.size == keys.size + 1 && (walletData["pgpKey"] as Map<*, *>).size == 2) {
            walletData
        } else {
            editor.clear()
            editor.apply()
            null
        }
    }

    fun saveWallet(wallet: Map<String, Any?>) {
        val editor = preferences.edit()
        for ((key, value) in wallet) {
            editor.putString(key, value.toString())
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

    fun getAllNodes(): String {
        for (i in 100..180) {
            val node = contract.callReadContract(
                credentials.address,
                "getNodeInfoById",
                CONET_GUARDIAN_NODES_CONTRACT_ADDRESS,
                listOf(
                    object : TypeReference<Utf8String>() {},
                    object : TypeReference<Utf8String>() {},
                    object : TypeReference<Utf8String>() {}
                ),
                i
            )

            println("NODE: $node")
        }

        return "nodes"
    }

    suspend fun startMiningCoroutine() {
        withContext(Dispatchers.IO) {
            val nodes = getAllNodes()

            println("NODES: $nodes")
        }
    }

    fun startMining() {
        runBlocking {
            launch {
                startMiningCoroutine()
            }
        }
    }

    fun createWalletStruct(walletInfo: Map<String, String>, keyPair: Map<String, String>): Map<String, Any?> {
        val wallet = mapOf(
            "tokens" to null,
            "publicKeyArmor" to walletInfo["address"],
            "keyID" to walletInfo["address"],
            "isPrimary" to true,
            "referrer" to null,
            "isNode" to false,
            "pgpKey" to mapOf(
                "public-key" to keyPair["public-key"],
                "private-key" to keyPair["private-key"]
            ),
            "privateKeyArmor" to walletInfo["private-key"],
            "hdPath" to "",
            "index" to 0,
        )

        return wallet
    }

    fun createWalletIfNotExists(passphrase: String = "", name: String = "", email: String = ""): Map<String, Any?> {
        val existingWallet = getLocalWallet()

        if (existingWallet != null) {
            println("WALLET EXISTS: " + existingWallet["privateKeyArmor"])

            return existingWallet
        } else {
            val mnemonic = generateMnemonic()
            val walletInfo = createWalletFromMnemonic(mnemonic)
            val keyPair = PGPUtil.generateKeyPair(passphrase, name, email)

            val wallet = createWalletStruct(walletInfo, keyPair)

            credentials = Credentials.create(wallet["privateKeyArmor"] as String)

            saveWallet(wallet)
            return wallet
        }
    }
}
