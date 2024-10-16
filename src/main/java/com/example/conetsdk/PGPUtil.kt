package com.example.conetsdk

import org.bouncycastle.bcpg.ArmoredOutputStream
import org.bouncycastle.bcpg.HashAlgorithmTags
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.openpgp.*
import org.bouncycastle.openpgp.operator.jcajce.*
import java.io.ByteArrayOutputStream
import java.security.*
import java.security.spec.RSAKeyGenParameterSpec
import java.util.Date

object PGPUtil {
    init {
        Security.addProvider(BouncyCastleProvider())
    }

    @Throws(PGPException::class, NoSuchAlgorithmException::class)
    fun generateKeyPair(passphrase: String, name: String, email: String): Map<String, String> {
        val rsaKeyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC")
        val spec = RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4)
        rsaKeyPairGenerator.initialize(spec)
        val keyPair = rsaKeyPairGenerator.generateKeyPair()

        val creationDate = Date(System.currentTimeMillis())
        val pgpKeyPair = JcaPGPKeyPair(PGPPublicKey.RSA_GENERAL, keyPair, creationDate)

        val digestCalculator = JcaPGPDigestCalculatorProviderBuilder().build().get(HashAlgorithmTags.SHA1)
        val pgpKeyRingGen = PGPKeyRingGenerator(
            PGPSignature.POSITIVE_CERTIFICATION,
            pgpKeyPair,
            email,
            digestCalculator,
            null, null,
            JcaPGPContentSignerBuilder(pgpKeyPair.publicKey.algorithm, HashAlgorithmTags.SHA256),
            JcePBESecretKeyEncryptorBuilder(PGPEncryptedData.AES_256, digestCalculator)
                .setProvider("BC")
                .build(passphrase.toCharArray())
        )

        val privateKeyRing = pgpKeyRingGen.generateSecretKeyRing()
        val publicKeyRing = pgpKeyRingGen.generatePublicKeyRing()

        val privateKey = String(armorKeyRing(privateKeyRing))
        val publicKey = String(armorKeyRing(publicKeyRing))

        return mapOf("public-key" to publicKey, "private-key" to privateKey)
    }

    private fun armorKeyRing(keyRing: PGPKeyRing): ByteArray {
        ByteArrayOutputStream().use { byteArrayOutputStream ->
            ArmoredOutputStream(byteArrayOutputStream).use { armoredStream ->
                keyRing.encode(armoredStream)
            }
            return byteArrayOutputStream.toByteArray()
        }
    }
}
