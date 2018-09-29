package by.htp.hvozdzeu.util;

import by.htp.hvozdzeu.exception.DataException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * The class for encoding password to md5Hex
 */
public final class PasswordEncoder {

    /**
     * Private constructor for realize Singleton pattern
     */
    private PasswordEncoder() {
    }

    /**
     * Singleton instance
     */
    private static volatile PasswordEncoder instance;

    /**
     * Singleton of encoding password
     *
     * @return instance
     */
    public static PasswordEncoder getInstance() {
        if (instance == null) {
            synchronized (PasswordEncoder.class) {
                if (instance == null) {
                    instance = new PasswordEncoder();
                }
            }
        }
        return instance;
    }

    /**
     * The method for encoding
     * @param s String password
     * @return String Encoded password
     */
    public String getEncodeData(String s) {
        if (s != null) {
            return DigestUtils.md5Hex(s);
        } else {
            throw new DataException(DataException.ERROR_MESSAGE_NO_DATA_ENCODE);
        }
    }

}
