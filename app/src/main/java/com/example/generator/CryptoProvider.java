package com.example.generator;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;


import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class CryptoProvider {
    public final static String PUBLIC_KEY =
        "MIIBITANBgkqhkiG9w0BAQEFAAOCAQ4AMIIBCQKCAQBut7NUjvC9kGJmD3qoaHCN" +
        "xGMscHziNXbUGaB2OqRZ8OWgu6BIyKw4ySrvxSXeJB8MseV5Y4irVrTov6rrhmpT" +
        "GhLZpNJUQi4qbjBYca9XABU3MQ7e7jn+8psGVJZmDEv25KHfvw6QfeIotBnYADFg" +
        "fCCmKsZBjdNquv6zFjHSZisAaqvPwuhRPpWFtvcL5ZS9ow1qMzQULIZPS9fYbO5S" +
        "Hyjs49B75GbA/yP4fHVeUdzfofMg8byckDUG5YyXb3ah4tuCS6c5Z83Ei3q6jXUL" +
        "gOxMXzDtX6re2eJz9qrbq9Bdg7cQ1fT5vfmzkfkzzE+jOYAJCfLhLM6gXqsuGf/N" +
        "AgMBAAE=";

    public static PublicKey readPublicKey() throws Exception {
        byte[] encoded = Base64.getDecoder().decode(PUBLIC_KEY.getBytes(StandardCharsets.UTF_8));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        return keyFactory.generatePublic(keySpec);
    }

    public static String encode(PublicKey publicKey, String message)
            throws Exception {
        Cipher encryptionCipher = Cipher.getInstance("RSA");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage =
                encryptionCipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedMessage);
    }

}
