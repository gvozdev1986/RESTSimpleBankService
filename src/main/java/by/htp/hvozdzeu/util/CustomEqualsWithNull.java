package by.htp.hvozdzeu.util;

/**
 * The class for equals with null
 */
public class CustomEqualsWithNull {

    private CustomEqualsWithNull() {
    }

    public static boolean customEquals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

}
