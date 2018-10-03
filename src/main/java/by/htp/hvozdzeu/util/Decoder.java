package by.htp.hvozdzeu.util;

import by.htp.hvozdzeu.dao.impl.BalanceBankAccountAccountRestInterfaceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * The class for encrypt and decrypt data from request (query) for security
 */
public final class Decoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceBankAccountAccountRestInterfaceImpl.class);
    private static SecretKeySpec secretKey;

    /**
     * Private constructor
     */
    private Decoder() {
    }

    /**
     * The method for change secret key on key for encrypt and decrypt methods.
     *
     * @param myKey String secret key
     */
    private static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            byte[] key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * The method for encode data
     *
     * @param strToEncrypt String strings for encode
     * @param secret       String secret key
     * @return String encoded data
     */
    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            LOGGER.error("Error while encrypting: {}", e.getMessage());
        }
        return null;
    }

    /**
     * The methods for decode data
     *
     * @param strToDecrypt String strings for decode
     * @param secret       String secret key
     * @return String decoded data
     */
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            LOGGER.error("Error while decrypting: {}", e.getMessage());
        }
        return null;
    }

}
