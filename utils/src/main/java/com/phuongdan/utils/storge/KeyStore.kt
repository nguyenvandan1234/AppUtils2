package com.phuongdan.utils.storge

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import javax.crypto.Cipher

class Store(var key: String) {
    companion object {
        var keyStore: KeyStore? = null
        var keyPair: KeyPair? = null
    }

    init {
        if (keyStore == null) {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore!!.load(null)
        }
        createKey(key)
    }
    private fun createKey(alias: String) {
        try {
            if (!keyStore!!.containsAlias(alias)) {
                val keyPairGenerator =
                        KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore")
                val parameterSpec = KeyGenParameterSpec.Builder(
                        alias,
                        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                        .setDigests(KeyProperties.DIGEST_SHA1)
                        .build()
                keyPairGenerator.initialize(parameterSpec)
                keyPair = keyPairGenerator.genKeyPair()
            } else {

            }
        } catch (e: Exception) {
        }
    }

    private fun getKeyInfo(alias: String) {
        val privateKey: PrivateKey =
                (keyStore?.getEntry(alias, null) as KeyStore.PrivateKeyEntry).privateKey
        val cert = keyStore?.getCertificate(alias)
        val publicKey = cert?.publicKey

        val publicKeyBytes: ByteArray = Base64.encode(publicKey!!.encoded, Base64.DEFAULT)
        val pubKeyString = String(publicKeyBytes)
    }

    fun encryptString(clearText: String, alias: String): String {
        try {
            val publicKey = keyStore!!.getCertificate(alias).publicKey
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, publicKey)
            return Base64.encodeToString(
                cipher.doFinal(clearText.toByteArray(Charsets.UTF_8)),
                Base64.DEFAULT
            )
        } catch (e: Exception) {
            throw e
            return ""
        }
    }

    fun decryptString(cipherText: String, alias: String): String {
        try {
            val privateKeyEntry = keyStore!!.getEntry(alias, null) as KeyStore.PrivateKeyEntry
            val privateKey = privateKeyEntry.privateKey
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.DECRYPT_MODE, privateKey)
            return String(cipher.doFinal(Base64.decode(cipherText, Base64.DEFAULT)))
        } catch (e: Exception) {
            throw e
            return ""
        }
    }

    private fun getAliases() {
        var aliasesString = ""
        val aliases = keyStore!!.aliases()
        while (aliases.hasMoreElements()) {
            aliasesString += "${aliases.nextElement()}, "
        }
    }


    private fun deleteKey(alias: String) {
        keyStore!!.deleteEntry(alias)
    }

}